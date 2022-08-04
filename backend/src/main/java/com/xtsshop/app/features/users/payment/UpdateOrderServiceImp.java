package com.xtsshop.app.features.users.payment;

import com.xtsshop.app.advices.exception.RecordNotFoundException;
import com.xtsshop.app.features.users.payment.models.UpdateOrderRequest;
import com.xtsshop.app.features.users.payment.models.UpdateOrderedItemsRequest;
import com.xtsshop.app.db.entities.Address;
import com.xtsshop.app.db.entities.Order;
import com.xtsshop.app.db.repositories.AddressJpaRepository;
import com.xtsshop.app.db.repositories.OrderJpaRepository;
import org.springframework.stereotype.Service;

@Service
public class UpdateOrderServiceImp implements UpdateOrderService {
    private UserPaymentRepo userPaymentRepo;
    private AddressJpaRepository addressJpaRepository;
    private OrderJpaRepository orderJpaRepository;
    private UpdateOrderedItemsService updateOrderedItemsService;

    private UpdateOrderRequest request;

    public UpdateOrderServiceImp(UserPaymentRepo userPaymentRepo, AddressJpaRepository addressJpaRepository, OrderJpaRepository orderJpaRepository, UpdateOrderedItemsService updateOrderedItemsService) {
        this.userPaymentRepo = userPaymentRepo;
        this.addressJpaRepository = addressJpaRepository;
        this.orderJpaRepository = orderJpaRepository;
        this.updateOrderedItemsService = updateOrderedItemsService;
    }

    public void update(UpdateOrderRequest request){
        this.request = request;
        Order order = updateShippingAddress();
        UpdateOrderedItemsRequest updateOrderedItemsRequest = new UpdateOrderedItemsRequest();
        updateOrderedItemsRequest.setOrder(order);
        updateOrderedItemsRequest.setItemQuantities(request.getItemQuantities());
        updateOrderedItemsService.update(updateOrderedItemsRequest);
    }

    private Order updateShippingAddress(){
        String paymentIntentId = request.getPaymentIntentId();
        Order order = userPaymentRepo.findOrderByUserPaymentIntentId(paymentIntentId)
                .orElseThrow(()->
                        new RecordNotFoundException("Order with payment intent" + paymentIntentId + " not found")
                );
        Address address = addressJpaRepository.findById(request.getAddressId())
                .orElseThrow(()-> new RecordNotFoundException("Address " + request.getAddressId() + " not found"));
        order.setShippingAddress(address);
        return orderJpaRepository.save(order);
    }
}
