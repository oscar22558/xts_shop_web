package com.xtsshop.app.db.seed;

import com.xtsshop.app.db.entities.*;
import com.xtsshop.app.db.repositories.ItemJpaRepository;
import com.xtsshop.app.db.repositories.OrderJpaRepository;
import com.xtsshop.app.db.repositories.UserJpaRepository;
import com.xtsshop.app.features.users.payment.models.FakePaymentDetail;
import com.xtsshop.app.helpers.DateTimeHelper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Component
@Transactional
public class OrderData {
    private UserJpaRepository userJpaRepository;
    private ItemJpaRepository itemJpaRepository;
    private OrderJpaRepository orderJpaRepository;

    private AppUser user;
    private Address address;
    private Date now;

    public OrderData(UserJpaRepository userJpaRepository, ItemJpaRepository itemJpaRepository, OrderJpaRepository orderJpaRepository) {
        this.userJpaRepository = userJpaRepository;
        this.itemJpaRepository = itemJpaRepository;
        this.orderJpaRepository = orderJpaRepository;
    }

    public void insert(){
        user = userJpaRepository.findUserByUsername("marry123");
        address = user.getAddresses().get(0);
        now = new DateTimeHelper().now();
        Order order = getOrder();
        orderJpaRepository.save(order);
    }

    private Order getOrder(){
        List<OrderedItem> orderItems = getOrderItems();
        Order order = new Order();
        order.setUser(user);
        order.setCreatedAt(now);
        order.setUpdatedAt(now);
        order.setStatus(OrderStatus.PAID);
        order.setPaymentIntentId(FakePaymentDetail.PAYMENT_INTENT_ID);
        order.setShippingAddress(address);

        orderItems.forEach(orderItem->orderItem.setOrder(order));
        order.setOrderedItems(orderItems);
        return order;
    }

    private List<OrderedItem> getOrderItems(){
        List<Item> item = getItems();
        List<OrderedItem> orderedItems = new ArrayList<>();

        OrderedItem orderedItem = new OrderedItem();
        orderedItem.setItem(item.get(0));
        orderedItem.setQuantity(3);
        orderedItem.setOrderPrice(item.get(0).getLatestPriceHistory().get());
        orderedItem.setCreatedAt(now);
        orderedItem.setUpdatedAt(now);
        orderedItems.add(orderedItem);

        orderedItem = new OrderedItem();
        orderedItem.setItem(item.get(1));
        orderedItem.setQuantity(3);
        orderedItem.setOrderPrice(item.get(1).getLatestPriceHistory().get());
        orderedItem.setCreatedAt(now);
        orderedItem.setUpdatedAt(now);
        orderedItems.add(orderedItem);

        return orderedItems;
    }

    private List<Item> getItems(){
        return itemJpaRepository.findAll();
    }
}
