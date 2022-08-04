package com.xtsshop.app.controller.orders;

import com.xtsshop.app.db.entities.*;
import com.xtsshop.app.db.entities.builder.OrderBuilder;
import com.xtsshop.app.db.entities.payment.Payment;
import com.xtsshop.app.db.entities.payment.PaymentType;
import com.xtsshop.app.db.repositories.ItemJpaRepository;
import com.xtsshop.app.db.repositories.OrderJpaRepository;
import com.xtsshop.app.db.repositories.PaymentJpaRepository;
import com.xtsshop.app.db.repositories.UserJpaRepository;
import com.xtsshop.app.helpers.DateTimeHelper;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Component("tests.http.orders.OrderWithPaymentData")
public class OrderWithPaymentData {
    private ItemJpaRepository itemJpaRepository;
    private UserJpaRepository userJpaRepository;
    private OrderJpaRepository orderJpaRepository;
    private PaymentJpaRepository paymentJpaRepository;

    public OrderWithPaymentData(ItemJpaRepository itemJpaRepository, UserJpaRepository userJpaRepository, OrderJpaRepository orderJpaRepository, PaymentJpaRepository paymentJpaRepository) {
        this.itemJpaRepository = itemJpaRepository;
        this.userJpaRepository = userJpaRepository;
        this.orderJpaRepository = orderJpaRepository;
        this.paymentJpaRepository = paymentJpaRepository;
    }

    public AppUser insertOrderWithPaymentForUser(AppUser user){
        Order order = new OrderBuilder()
                .setUser(user)
                .setShippingAddress(user.getAddresses().get(0))
                .setStatus(OrderStatus.PAID)
                .setOrderedItems(createOrderItems())
                .build();
        createPaymentForUser(order);
        user.getOrders().add(order);
        orderJpaRepository.save(order);
        return userJpaRepository.save(user);
    }
    public List<OrderedItem> createOrderItems(){
        List<Item> itemList = itemJpaRepository.findAll();
        Date now = new DateTimeHelper().now();
        OrderedItem orderedItem = new OrderedItem(now, now, itemList.get(0), 4);
        orderedItem.setOrderPrice(itemList.get(0).getPriceHistories().get(0));
        List<OrderedItem> orderedItems = new ArrayList<>();
        orderedItems.add(orderedItem);
        orderedItems.add(new OrderedItem(now, now, itemList.get(1), 2));
        orderedItems.add(new OrderedItem(now, now, itemList.get(3), 6));
        return orderedItems;
    }
    public void createPaymentForUser(Order order){
        Date now = new DateTimeHelper().now();
        Payment payment = new Payment();
        payment.setPaidTotal(426.4f);
        payment.setUpdatedAt(now);
        payment.setCreatedAt(now);
        payment.setPaymentType(PaymentType.CREDIT_CARD);
        order.setPayment(payment);
        payment.setOrder(order);
        paymentJpaRepository.save(payment);
    }
}
