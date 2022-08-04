package com.xtsshop.app.features.orders;

import com.xtsshop.app.advices.exception.RecordNotFoundException;
import com.xtsshop.app.advices.exception.*;
import com.xtsshop.app.features.orders.exceptions.InsufficientItemStockException;
import com.xtsshop.app.features.orders.exceptions.ItemOutOfStockException;
import com.xtsshop.app.features.orders.exceptions.ItemPriceNotDefinedException;
import com.xtsshop.app.features.orders.exceptions.OrderStatusUpdateException;
import com.xtsshop.app.features.users.addresses.models.AddressCreateRequestConvertor;
import com.xtsshop.app.db.entities.*;
import com.xtsshop.app.db.entities.builder.OrderBuilder;
import com.xtsshop.app.db.entities.payment.Payment;
import com.xtsshop.app.db.repositories.*;
import com.xtsshop.app.features.orders.models.OrderCreateRequest;
import com.xtsshop.app.features.users.addresses.models.AddressCreateRequest;
import com.xtsshop.app.features.orders.models.PaymentCreateRequest;
import com.xtsshop.app.features.items.QueryItemsService;
import com.xtsshop.app.features.users.AllowOnlySameUserService;
import com.xtsshop.app.features.users.TargetUserService;
import com.xtsshop.app.helpers.DateTimeHelper;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrdersService {
    private OrderJpaRepository repository;
    private PaymentJpaRepository paymentJpaRepository;
    private UserJpaRepository userJpaRepository;
    private AddressesService addressesService;
    private TargetUserService targetUserService;
    private AllowOnlySameUserService allowOnlySameUserService;
    private OrderAuthorizationService orderAuthorizationService;
    private QueryItemsService queryItemsService;

    public OrdersService(
            OrderJpaRepository repository,
            AddressesService addressesService,
            TargetUserService targetUserService,
            OrderAuthorizationService orderAuthorizationService,
            QueryItemsService queryItemsService,
            PaymentJpaRepository paymentJpaRepository,
            UserJpaRepository userJpaRepository,
            AllowOnlySameUserService allowOnlySameUserService
    ) {
        this.repository = repository;
        this.addressesService = addressesService;
        this.targetUserService = targetUserService;
        this.orderAuthorizationService = orderAuthorizationService;
        this.queryItemsService = queryItemsService;
        this.paymentJpaRepository = paymentJpaRepository;
        this.userJpaRepository = userJpaRepository;
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
                        Date now = new DateTimeHelper().now();
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
        userJpaRepository.save(user);
        return repository.save(order);
    }

    public Order cancel(Long id){
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

    public Order pay(Long orderId, PaymentCreateRequest paymentCreateRequest){
        Order order = get(orderId);
        if(!allowOnlySameUserService.canUserAccess(order.getUser().getUsername())){
            throw new UnAuthorizationException();
        }
        if(order.getStatus() != OrderStatus.WAITING_PAYMENT){
            throw new OrderStatusUpdateException("Order is paid.");
        }

        float orderedItemsPriceTotal = getItemsPriceTotal(order);
        if(paymentCreateRequest.getPaidTotal() != orderedItemsPriceTotal){
            throw new OrderStatusUpdateException("Paid total and item price total is not the same");
        }

        Payment payment = paymentCreateRequest.toEntity();
        payment.setOrder(order);
        payment.setPaidTotal(paymentCreateRequest.getPaidTotal());

        order.setPayment(payment);
        order.setStatus(OrderStatus.PAID);
        order.getOrderedItems().forEach(orderedItem->{
            Optional<PriceHistory> history = orderedItem.getItem().getLatestPriceHistory();
            try {
                orderedItem.setOrderPrice(history.orElseThrow(()-> ItemPriceNotDefinedException.build(null)));
            } catch (ItemPriceNotDefinedException e) {
                throw new RuntimeException(e);
            }
        });
        paymentJpaRepository.save(payment);
        return repository.save(order);
    }

    public Order startProcessing(Long id){
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

    public Order ship(Long id){
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
    public Order finishShipping(Long id){
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
            Address address = new AddressCreateRequestConvertor(request.getAddressCreateRequest(), user)
                    .toEntity();
            user.getAddresses().add(address);
            return address;
        }else{
            return addressesService.get(request.getAddressId());
        }
    }

    public float getItemsPriceTotal(Order order){
        return order.getOrderedItems().stream()
                .map(orderItem->{
                    try {
                        float priceFloat = orderItem.getOrderPriceValue();
                        return priceFloat * orderItem.getQuantity();
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
