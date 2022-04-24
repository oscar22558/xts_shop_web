package com.xtsshop.app.service.orders;

import com.xtsshop.app.advice.exception.ItemPriceNotDefinedException;
import com.xtsshop.app.advice.exception.RecordNotFoundException;
import com.xtsshop.app.advice.exception.OrderStatusUpdateException;
import com.xtsshop.app.advice.exception.UnAuthorizationException;
import com.xtsshop.app.db.entities.*;
import com.xtsshop.app.db.entities.builder.AddressBuilder;
import com.xtsshop.app.db.entities.builder.OrderBuilder;
import com.xtsshop.app.db.entities.payment.Payment;
import com.xtsshop.app.db.repositories.ItemRepository;
import com.xtsshop.app.db.repositories.OrderRepository;
import com.xtsshop.app.db.repositories.PriceHistoryRepository;
import com.xtsshop.app.request.orders.OrderCreateRequest;
import com.xtsshop.app.request.users.addresses.AddressCreateRequest;
import com.xtsshop.app.request.orders.PaymentCreateRequest;
import com.xtsshop.app.service.addresses.AddressesCRUDService;
import com.xtsshop.app.service.items.ItemsService;
import com.xtsshop.app.service.payments.PaymentsService;
import com.xtsshop.app.service.users.TargetUserService;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class OrdersService {
    private OrderRepository repository;
    private PaymentsService paymentsService;
    private AddressesCRUDService addressesCRUDService;
    private TargetUserService targetUserService;
    private ItemRepository itemRepository;
    private OrderAuthorizationService orderAuthorizationService;
    private PriceHistoryRepository priceHistoryRepository;
    private ItemsService itemsService;
    public OrdersService(
            OrderRepository repository,
            PaymentsService paymentsService,
            AddressesCRUDService addressesCRUDService,
            TargetUserService targetUserService,
            ItemRepository itemRepository,
            OrderAuthorizationService orderAuthorizationService,
            PriceHistoryRepository priceHistoryRepository,
            ItemsService itemsService
    ) {
        this.repository = repository;
        this.paymentsService = paymentsService;
        this.addressesCRUDService = addressesCRUDService;
        this.targetUserService = targetUserService;
        this.itemRepository = itemRepository;
        this.orderAuthorizationService = orderAuthorizationService;
        this.priceHistoryRepository = priceHistoryRepository;
        this.itemsService = itemsService;
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
                        OrderedItem orderedItem = new OrderedItem();
                        orderedItem.setItem(itemEntity);
                        orderedItem.setQuantity(item.getQuantity());
                        return orderedItem;
                    } catch (RecordNotFoundException e) {
                        throw new RuntimeException(e);
                    }

                })
                .collect(Collectors.toList());
        AppUser user = targetUserService.getUser(request.getUsername());
        Address address = getAddress(request, user);
        OrderStatus status = OrderStatus.WAITING_PAYMENT;
        Order order = new OrderBuilder()
                .setShippingAddress(address)
                .setUser(user)
                .setStatus(status)
                .setOrderedItems(orderedItems)
                .build();
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
        order.setPayment(payment);
        payment.setOrder(order);
        order.setStatus(OrderStatus.PAID);
        order.getOrderedItems().forEach(orderedItem->{
            Optional<PriceHistory> history = orderedItem.getItem().getLatestPriceHistory();
            try {
                orderedItem.setOrderPrice(history.orElseThrow(()->ItemPriceNotDefinedException.build(null)));
            } catch (ItemPriceNotDefinedException e) {
                throw new RuntimeException(e);
            }
        });
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
