package com.xtsshop.app.service.orders;

import com.xtsshop.app.advice.exception.RecordNotFoundException;
import com.xtsshop.app.advice.exception.OrderStatusUpdateException;
import com.xtsshop.app.advice.exception.UnAuthorizationException;
import com.xtsshop.app.db.entities.*;
import com.xtsshop.app.db.entities.builder.AddressBuilder;
import com.xtsshop.app.db.entities.builder.OrderBuilder;
import com.xtsshop.app.db.entities.payment.Payment;
import com.xtsshop.app.db.repositories.ItemRepository;
import com.xtsshop.app.db.repositories.OrderRepository;
import com.xtsshop.app.request.orders.OrderCreateRequest;
import com.xtsshop.app.request.users.addresses.AddressCreateRequest;
import com.xtsshop.app.request.users.payments.PaymentCreateRequest;
import com.xtsshop.app.service.addresses.AddressesCRUDService;
import com.xtsshop.app.service.payments.PaymentsService;
import com.xtsshop.app.service.users.TargetUserService;
import com.xtsshop.app.viewmodel.OrderModel;
import com.xtsshop.app.viewmodel.builder.OrderModelBuilder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OrdersService {
    private OrderRepository repository;
    private PaymentsService paymentsService;
    private AddressesCRUDService addressesCRUDService;
    private TargetUserService targetUserService;
    private ItemRepository itemRepository;
    private OrderAuthorizationService orderAuthorizationService;
    public OrdersService(
            OrderRepository repository,
            PaymentsService paymentsService,
            AddressesCRUDService addressesCRUDService,
            TargetUserService targetUserService,
            ItemRepository itemRepository,
            OrderAuthorizationService orderAuthorizationService
    ) {
        this.repository = repository;
        this.paymentsService = paymentsService;
        this.addressesCRUDService = addressesCRUDService;
        this.targetUserService = targetUserService;
        this.itemRepository = itemRepository;
        this.orderAuthorizationService = orderAuthorizationService;
    }

    public List<OrderModel> all(){
        return repository.findAll().stream().map(item-> new OrderModelBuilder()
                .setEntity(item)
                .build())
                .collect(Collectors.toList());
    }
    public Order get(Long id) throws RecordNotFoundException, UnAuthorizationException {
        Order order =  repository.findById(id).orElseThrow(()->new RecordNotFoundException("Order with id "+id+" not found."));
        orderAuthorizationService.isAuthorized(order);
        return order;
    }

    public Order create(OrderCreateRequest request) throws RecordNotFoundException, UnAuthorizationException {
        Set<Item> items = new HashSet<>(itemRepository.findAllById(request.getPriceHistoryIds()));
        AppUser user = targetUserService.getUser(request.getUsername());
        Payment payment = request.getPaymentId() != null ? paymentsService.get(request.getPaymentId()) : null;
        Address address = getAddress(request, user);
        OrderStatus status;
        if(payment == null){
            status = OrderStatus.WAITING_PAYMENT;
        }else{
            status = OrderStatus.PAID;
        }
        Order order = new OrderBuilder()
                .setPayment(payment)
                .setShippingAddress(address)
                .setUser(user)
                .setStatus(status)
                .setOrderedItems(items)
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
        Payment payment = paymentCreateRequest.toEntity();
        order.setPayment(payment);
        payment.setOrder(order);
        order.setStatus(OrderStatus.PAID);
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

}
