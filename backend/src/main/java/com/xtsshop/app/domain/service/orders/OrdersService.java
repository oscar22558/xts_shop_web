package com.xtsshop.app.domain.service.orders;

import com.xtsshop.app.advice.exception.*;
import com.xtsshop.app.db.entities.*;
import com.xtsshop.app.db.entities.builder.AddressBuilder;
import com.xtsshop.app.db.entities.builder.OrderBuilder;
import com.xtsshop.app.db.entities.payment.Payment;
import com.xtsshop.app.db.repositories.*;
import com.xtsshop.app.domain.request.orders.OrderCreateRequest;
import com.xtsshop.app.domain.request.users.addresses.AddressCreateRequest;
import com.xtsshop.app.domain.request.orders.PaymentCreateRequest;
import com.xtsshop.app.domain.service.addresses.AddressesService;
import com.xtsshop.app.domain.service.items.QueryItemsService;
import com.xtsshop.app.domain.service.users.AllowOnlySameUserService;
import com.xtsshop.app.domain.service.users.TargetUserService;
import com.xtsshop.app.util.DateTimeUtil;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrdersService {
    private OrderRepository repository;
    private PaymentRepository paymentRepository;
    private UserRepository userRepository;
    private AddressesService addressesService;
    private TargetUserService targetUserService;
    private AllowOnlySameUserService allowOnlySameUserService;
    private OrderAuthorizationService orderAuthorizationService;
    private QueryItemsService queryItemsService;
    public OrdersService(
            OrderRepository repository,
            AddressesService addressesService,
            TargetUserService targetUserService,
            OrderAuthorizationService orderAuthorizationService,
            QueryItemsService queryItemsService,
            PaymentRepository paymentRepository,
            UserRepository userRepository,
            AllowOnlySameUserService allowOnlySameUserService
    ) {
        this.repository = repository;
        this.addressesService = addressesService;
        this.targetUserService = targetUserService;
        this.orderAuthorizationService = orderAuthorizationService;
        this.queryItemsService = queryItemsService;
        this.paymentRepository = paymentRepository;
        this.userRepository = userRepository;
        this.allowOnlySameUserService = allowOnlySameUserService;
    }

    public List<Order> all(){
        return repository.findAll();
    }
    public Order get(Long id) throws RecordNotFoundException, UnAuthorizationException {
        Order order =  repository.findById(id).orElseThrow(()->new RecordNotFoundException("Order with id "+id+" not found."));
        orderAuthorizationService.isAuthorized(order);
        return order;
    }

    public Order create(OrderCreateRequest request) throws RecordNotFoundException, UnAuthorizationException {
        List<OrderedItem> orderedItems = request.getOrderedItems().stream()
                .map(item->{
                    try {
                        Item itemEntity = queryItemsService.get(item.getItemId());
                        Date now = new DateTimeUtil().now();
                        OrderedItem orderedItem = new OrderedItem();
                        orderedItem.setCreatedAt(now);
                        orderedItem.setUpdatedAt(now);
                        orderedItem.setItem(itemEntity);
                        orderedItem.setQuantity(item.getQuantity());
                        updateItemStock(itemEntity, item.getQuantity());
                        return orderedItem;
                    } catch (RecordNotFoundException | ItemOutOfStockException | InsufficientItemStockException e) {
                        throw new RuntimeException(e);
                    }
                })
                .collect(Collectors.toList());
        AppUser user = allowOnlySameUserService.getUser(request.getUsername());
        Address address = getAddress(request, user);
        OrderStatus status = OrderStatus.WAITING_PAYMENT;
        Order order = new OrderBuilder()
                .setShippingAddress(address)
                .setUser(user)
                .setStatus(status)
                .setOrderedItems(orderedItems)
                .build();
        user.getOrders().add(order);
        userRepository.save(user);
        return repository.save(order);
    }
    public Order cancel(Long id) throws RecordNotFoundException, OrderStatusUpdateException, UnAuthorizationException{
        Order order = get(id);
        if(order.getStatus() == OrderStatus.SHIPPED || order.getStatus() == OrderStatus.SHIPPING)
            throw new OrderStatusUpdateException("Order cannot be canceled.");
        order.setStatus(OrderStatus.CANCELED);
        order.getOrderedItems().forEach(orderedItem->{
            int updatedItemStock = orderedItem.getItem().getStock() + orderedItem.getQuantity();
            orderedItem.getItem().setStock(updatedItemStock);
        });
        return repository.save(order);
    }
    public Order pay(Long id, PaymentCreateRequest paymentCreateRequest) throws RecordNotFoundException, OrderStatusUpdateException, UnAuthorizationException{
        Order order = get(id);
        if(!allowOnlySameUserService.canUserAccess(order.getUser().getUsername())){
            throw new UnAuthorizationException();
        }
        if(order.getStatus() != OrderStatus.WAITING_PAYMENT){
            throw new OrderStatusUpdateException("Order is paid.");
        }
        if(paymentCreateRequest.getPaidTotal() != paymentCreateRequest.getOrderItemPriceTotal())
            throw new OrderStatusUpdateException("Paid total and item price total is not the same");
        Payment payment = paymentCreateRequest.toEntity();
        payment.setOrder(order);
        payment.setPaidTotal(paymentCreateRequest.getPaidTotal());

        order.setPayment(payment);
        order.setStatus(OrderStatus.PAID);
        order.getOrderedItems().forEach(orderedItem->{
            Optional<PriceHistory> history = orderedItem.getItem().getLatestPriceHistory();
            try {
                orderedItem.setOrderPrice(history.orElseThrow(()->ItemPriceNotDefinedException.build(null)));
            } catch (ItemPriceNotDefinedException e) {
                throw new RuntimeException(e);
            }
        });
        paymentRepository.save(payment);
        return repository.save(order);
    }
    public Order startProcessing(Long id) throws RecordNotFoundException, OrderStatusUpdateException, UnAuthorizationException{
        Order order = get(id);
        if(order.getStatus() == OrderStatus.WAITING_PAYMENT){
            throw new OrderStatusUpdateException("Order is not paid.");
        }else if(order.getStatus() == OrderStatus.SHIPPING){
            throw new OrderStatusUpdateException("Order is shipping.");
        }else if(order.getStatus() == OrderStatus.SHIPPED){
            throw new OrderStatusUpdateException("Order is shipped.");
        }
        order.setStatus(OrderStatus.PROCESSING);
        return repository.save(order);
    }
    public Order ship(Long id) throws RecordNotFoundException, OrderStatusUpdateException, UnAuthorizationException{
        Order order = get(id);
        if(order.getStatus() == OrderStatus.WAITING_PAYMENT){
            throw new OrderStatusUpdateException("Order is not paid.");
        }else if(order.getStatus() == OrderStatus.PAID){
            throw new OrderStatusUpdateException("Order is waiting for processing.");
        }else if(order.getStatus() == OrderStatus.SHIPPED){
            throw new OrderStatusUpdateException("Order is shipped.");
        }
        order.setStatus(OrderStatus.SHIPPING);
        return repository.save(order);
    }
    public Order finishShipping(Long id) throws RecordNotFoundException, OrderStatusUpdateException, UnAuthorizationException{
        Order order = get(id);
        if(order.getStatus() == OrderStatus.WAITING_PAYMENT){
            throw new OrderStatusUpdateException("Order is not paid.");
        }else if(order.getStatus() == OrderStatus.PAID){
            throw new OrderStatusUpdateException("Order is waiting for processing.");
        }else if(order.getStatus() == OrderStatus.PROCESSING){
            throw new OrderStatusUpdateException("Order is still processing.");
        }
        order.setStatus(OrderStatus.SHIPPED);
        return repository.save(order);
    }
    private Address getAddress(OrderCreateRequest request, AppUser user) throws RecordNotFoundException{
        AddressCreateRequest addressCreateRequest = request.getAddressCreateRequest();
        if (addressCreateRequest != null) {
            Address address = new AddressBuilder()
                    .setCity(addressCreateRequest.getCity())
                    .setCountry(addressCreateRequest.getCountry())
                    .setRow1(addressCreateRequest.getRow1())
                    .setRow2(addressCreateRequest.getRow2())
                    .setRow3(addressCreateRequest.getRow3())
                    .setUser(user)
                    .build();
            user.getAddresses().add(address);
            return address;
        }else{
            return addressesService.get(request.getAddressId());
        }
    }
    public float getItemPriceTotal(Order order){
        return order.getOrderedItems().stream()
                .map(orderItem->{
                    try {
                        PriceHistory price = Optional.ofNullable(orderItem.getOrderPrice())
                            .orElse(orderItem.getItem().getLatestPriceHistory().orElseThrow(()-> ItemPriceNotDefinedException.build()));
                        return price.getValue() * orderItem.getQuantity();
                    }catch (ItemPriceNotDefinedException ex){
                        throw new RuntimeException(ex);
                    }
                })
                .reduce(0f, Float::sum);
    }
    public void updateItemStock(Item item, int orderedQuantity) throws InsufficientItemStockException, ItemOutOfStockException {
        int itemStock = item.getStock();
        if(itemStock - orderedQuantity < 0){
            throw new InsufficientItemStockException("Item "+item.getName()+" does not have enough stock");
        }
        if(itemStock <= 0){
            throw ItemOutOfStockException.build();
        }
        item.setStock(itemStock - orderedQuantity);

    }
}
