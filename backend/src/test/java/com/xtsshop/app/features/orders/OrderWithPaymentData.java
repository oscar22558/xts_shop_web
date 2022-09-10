package com.xtsshop.app.features.orders;

import com.xtsshop.app.db.entities.*;
import com.xtsshop.app.features.orders.entitybuilders.OrderEntityBuilder;
import com.xtsshop.app.db.repositories.ItemJpaRepository;
import com.xtsshop.app.db.repositories.OrderJpaRepository;
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

    public OrderWithPaymentData(ItemJpaRepository itemJpaRepository, UserJpaRepository userJpaRepository, OrderJpaRepository orderJpaRepository) {
        this.itemJpaRepository = itemJpaRepository;
        this.userJpaRepository = userJpaRepository;
        this.orderJpaRepository = orderJpaRepository;
    }

    public AppUser insertOrderWithPaymentForUser(AppUser user){
        Order order = new OrderEntityBuilder()
                .setUser(user)
                .setShippingAddress(user.getAddresses().get(0))
                .setStatus(OrderStatus.PAID)
                .setOrderedItems(createOrderItems())
                .build();
        user.getOrders().add(order);
        orderJpaRepository.save(order);
        return userJpaRepository.save(user);
    }
    public List<OrderedItem> createOrderItems(){
        List<Item> itemList = itemJpaRepository.findAll();
        Date now = new DateTimeHelper().now();

        OrderedItem orderedItem = new OrderedItem(now, now, itemList.get(0), 4);
        float orderItemPrice = itemList.get(0).getPriceHistories().get(0).getValue();
        orderedItem.setPrice(orderItemPrice);

        List<OrderedItem> orderedItems = new ArrayList<>();
        orderedItems.add(orderedItem);
        orderedItems.add(new OrderedItem(now, now, itemList.get(1), 2));
        orderedItems.add(new OrderedItem(now, now, itemList.get(3), 6));

        return orderedItems;
    }

}
