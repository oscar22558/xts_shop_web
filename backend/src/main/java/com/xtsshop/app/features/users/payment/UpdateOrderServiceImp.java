package com.xtsshop.app.features.users.payment;

import com.xtsshop.app.advices.exception.RecordNotFoundException;
import com.xtsshop.app.db.entities.Invoice;
import com.xtsshop.app.db.entities.ShippingAddress;
import com.xtsshop.app.features.orders.entitybuilders.ShippingAddressEntityBuilder;
import com.xtsshop.app.features.users.payment.models.UpdateOrderRequest;
import com.xtsshop.app.features.users.payment.models.UpdateOrderedItemsRequest;
import com.xtsshop.app.db.entities.Address;
import com.xtsshop.app.db.entities.Order;
import com.xtsshop.app.db.repositories.AddressJpaRepository;
import com.xtsshop.app.db.repositories.OrderJpaRepository;
import com.xtsshop.app.helpers.DateTimeHelper;
import org.springframework.stereotype.Service;

import java.sql.Date;

@Service
public class UpdateOrderServiceImp implements UpdateOrderService {
    private UserPaymentRepo userPaymentRepo;
    private AddressJpaRepository addressJpaRepository;
    private OrderJpaRepository orderJpaRepository;
    private UpdateOrderedItemsService updateOrderedItemsService;

    private UpdateOrderRequest request;
    private Order order;

    public UpdateOrderServiceImp(UserPaymentRepo userPaymentRepo, AddressJpaRepository addressJpaRepository, OrderJpaRepository orderJpaRepository, UpdateOrderedItemsService updateOrderedItemsService) {
        this.userPaymentRepo = userPaymentRepo;
        this.addressJpaRepository = addressJpaRepository;
        this.orderJpaRepository = orderJpaRepository;
        this.updateOrderedItemsService = updateOrderedItemsService;
    }

    public void update(UpdateOrderRequest request){
        this.request = request;
        getOrder();
        updateShippingAddress();
        updateInvoice();
        orderJpaRepository.save(order);
        UpdateOrderedItemsRequest updateOrderedItemsRequest = new UpdateOrderedItemsRequest();
        updateOrderedItemsRequest.setOrder(order);
        updateOrderedItemsRequest.setItemQuantities(request.getItemQuantities());
        updateOrderedItemsService.update(updateOrderedItemsRequest);
    }

    private void getOrder(){
        String paymentIntentId = request.getPaymentIntentId();
        order = userPaymentRepo.findOrderByUserPaymentIntentId(paymentIntentId)
                .orElseThrow(()->
                        new RecordNotFoundException("Order with payment intent" + paymentIntentId + " not found")
                );
    }

    private void updateShippingAddress(){
        Date now = new DateTimeHelper().now();
        Address address = addressJpaRepository.findById(request.getAddressId())
                .orElseThrow(()-> new RecordNotFoundException("Address " + request.getAddressId() + " not found"));
        ShippingAddress shippingAddressEntity = order.getShippingAddress();
        shippingAddressEntity.setUpdatedAt(now);
        shippingAddressEntity.setRow1(address.getRow1());
        shippingAddressEntity.setRow2(address.getRow2());
        shippingAddressEntity.setDistrict(address.getDistrict());
        shippingAddressEntity.setArea(address.getArea());
        shippingAddressEntity.setCity(address.getCity());
        shippingAddressEntity.setCountry(address.getCountry());
    }

    private void updateInvoice(){
       Invoice invoice = order.getInvoice();
       Invoice newInvoice = request.getInvoice();
       invoice.setShippingFee(newInvoice.getShippingFee());
       invoice.setItemsTotal(newInvoice.getItemsTotal());
       invoice.setTotal(newInvoice.getTotal());
    }
}
