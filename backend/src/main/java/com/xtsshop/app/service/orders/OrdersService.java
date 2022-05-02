package com.xtsshop.app.service.orders;

import com.xtsshop.app.advice.exception.ItemPriceNotDefinedException;
import com.xtsshop.app.advice.exception.RecordNotFoundException;
import com.xtsshop.app.advice.exception.OrderStatusUpdateException;
import com.xtsshop.app.advice.exception.UnAuthorizationException;
import com.xtsshop.app.db.entities.*;
import com.xtsshop.app.db.entities.builder.AddressBuilder;
import com.xtsshop.app.db.entities.builder.OrderBuilder;
import com.xtsshop.app.db.entities.payment.Payment;
import com.xtsshop.app.db.repositories.*;
import com.xtsshop.app.request.orders.OrderCreateRequest;
import com.xtsshop.app.request.users.addresses.AddressCreateRequest;
import com.xtsshop.app.request.orders.PaymentCreateRequest;
import com.xtsshop.app.service.addresses.AddressesCRUDService;
import com.xtsshop.app.service.items.ItemsService;
import com.xtsshop.app.service.payments.PaymentsService;
import com.xtsshop.app.service.users.TargetUserService;
import com.xtsshop.app.util.DateTimeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private AddressesCRUDService addressesCRUDService;
    private TargetUserService targetUserService;
    private OrderAuthorizationService orderAuthorizationService;
    private ItemsService itemsService;
    public OrdersService(
            OrderRepository repository,
            AddressesCRUDService addressesCRUDService,
            TargetUserService targetUserService,
            OrderAuthorizationService orderAuthorizationService,
            ItemsService itemsService,
            PaymentRepository paymentRepository,
            UserRepository userRepository
    ) {
        this.repository = repository;
        this.addressesCRUDService = addressesCRUDService;
        this.targetUserService = targetUserService;
        this.orderAuthorizationService = orderAuthorizationService;
        this.itemsService = itemsService;
        this.paymentRepository = paymentRepository;
        this.userRepository = userRepository;
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
                        Item itemEntity = itemsService.get(item.getItemId());
                        Date now = new DateTimeUtil().now();
                        OrderedItem orderedItem = new OrderedItem();
                        orderedItem.setCreatedAt(now);
                        orderedItem.setUpdatedAt(now);
                        orderedItem.setItem(itemEntity);
                        orderedItem.setOrderPrice(itemEntity.getLatestPriceHistory().orElseThrow());
                        orderedItem.setQuantity(item.getQuantity());
                        return orderedItem;
                    } catch (RecordNotFoundException e) {
                        throw new RuntimeException(e);
                    }

                })
                .collect(Collectors.toList());
        AppUser user = targetUserService.getUser(null);
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
        return repository.save(order);
    }
    public Order pay(Long id, PaymentCreateRequest paymentCreateRequest) throws RecordNotFoundException, OrderStatusUpdateException, UnAuthorizationException{
        Order order = get(id);
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
            return addressesCRUDService.get(request.getAddressId());
        }
    }
    public float getItemPriceTotal(Order order){
        return order.getOrderedItems().stream()
                .map(orderItem->orderItem.getOrderPrice().getValue() * orderItem.getQuantity())
                .reduce(0f, Float::sum);
    }
}
