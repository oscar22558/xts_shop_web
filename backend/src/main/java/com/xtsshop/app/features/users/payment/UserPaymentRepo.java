package com.xtsshop.app.features.users.payment;

import com.stripe.model.PaymentIntent;
import com.xtsshop.app.advices.exception.RecordNotFoundException;
import com.xtsshop.app.db.entities.Address;
import com.xtsshop.app.db.entities.Item;
import com.xtsshop.app.db.entities.Order;
import com.xtsshop.app.db.repositories.AddressJpaRepository;
import com.xtsshop.app.db.repositories.ItemJpaRepository;
import com.xtsshop.app.db.repositories.OrderJpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
public class UserPaymentRepo {
    private ItemJpaRepository itemJpaRepository;
    private AddressJpaRepository addressJpaRepository;
    private OrderJpaRepository orderJpaRepository;

    public UserPaymentRepo(ItemJpaRepository itemJpaRepository, AddressJpaRepository addressJpaRepository, OrderJpaRepository orderJpaRepository) {
        this.itemJpaRepository = itemJpaRepository;
        this.addressJpaRepository = addressJpaRepository;
        this.orderJpaRepository = orderJpaRepository;
    }

    public Item findItemById(long itemId){
        return itemJpaRepository.findById(itemId)
                .orElseThrow(()->new RecordNotFoundException("Item " + itemId + " not found."));
    }

    public float getCurrentItemPriceByItemId(long itemId){
        return findItemById(itemId).getPrice();
    }

    public Address findAddressByAddressId(long addressId){
        return addressJpaRepository
                .findById(addressId)
                .orElseThrow(()->new RecordNotFoundException(("Address " + addressId + " not found.")));
    }

    public Optional<Order> findOrderByUserPaymentIntent(PaymentIntent paymentIntent){
        return orderJpaRepository.findByPaymentIntentId(paymentIntent.getId());
    }

    public Optional<Order> findOrderByUserPaymentIntentId(String paymentIntentId){
        return orderJpaRepository.findByPaymentIntentId(paymentIntentId);
    }

    public void saveOrder(Order order){
        orderJpaRepository.save(order);
    }

    public void deleteOrderByPaymentIntentId(String paymentIntentId){
        orderJpaRepository.findByPaymentIntentId(paymentIntentId).ifPresent(order->{
            order.setUser(null);
            orderJpaRepository.delete(order);
        });
    }

}
