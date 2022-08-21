package com.xtsshop.app.features.orders.data;

import com.xtsshop.app.db.entities.*;
import com.xtsshop.app.db.repositories.OrderedItemJpaRepository;
import com.xtsshop.app.features.orders.entitybuilders.ShippingAddressEntityBuilder;
import com.xtsshop.app.helpers.DateTimeHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.TestComponent;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.xtsshop.app.db.repositories.OrderJpaRepository;
import com.xtsshop.app.db.repositories.UserJpaRepository;

import com.xtsshop.app.features.users.payment.models.FakePaymentDetail;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@TestComponent
@Transactional
public abstract class FakeOrderDataSet {
    private UserJpaRepository userJpaRepository;
    private OrderJpaRepository orderJpaRepository;
    private OrderedItemJpaRepository orderedItemJpaRepository;
    private Order order;

    public FakeOrderDataSet(UserJpaRepository userJpaRepository, OrderJpaRepository orderJpaRepository, OrderedItemJpaRepository orderedItemJpaRepository) {
        this.userJpaRepository = userJpaRepository;
        this.orderJpaRepository = orderJpaRepository;
        this.orderedItemJpaRepository = orderedItemJpaRepository;
    }

    public void insertOrderForUser(){
        Date now = new DateTimeHelper().now();
        AppUser user = userJpaRepository.findUserByUsername(getUsername());
        Address address = user.getAddresses().get(0);
        Order order = new Order();
        order.setCreatedAt(now);
        order.setUpdatedAt(now);
        order.setUser(user);

        ShippingAddress shippingAddress = new ShippingAddressEntityBuilder(address).build();
        order.setShippingAddress(shippingAddress);

        order.setStatus(OrderStatus.WAITING_PAYMENT);
        order.setOrderedItems(new ArrayList<>());
        order.setPaymentIntentId(FakePaymentDetail.PAYMENT_INTENT_ID);
        this.order = orderJpaRepository.save(order);
        user.getOrders().add(order);
        addOrderedItemsList();
    }

    private void addOrderedItemsList(){
        List<OrderedItem> orderedItems = getOrderItems();
        orderedItems.forEach(orderedItem -> orderedItem.setOrder(order));
        orderedItemJpaRepository.saveAll(orderedItems);
        order.getOrderedItems().addAll(orderedItems);
    }

    protected abstract List<OrderedItem> getOrderItems();

    protected abstract String getUsername();
}
