package com.xtsshop.app.db.entities.builder;

import com.xtsshop.app.db.entities.*;
import com.xtsshop.app.helpers.DateTimeHelper;

import java.sql.Date;
import java.util.List;

public class OrderBuilder {

    private Address shippingAddress;
    private AppUser user;
    private OrderStatus status;
    private List<OrderedItem> orderedItems;

    public OrderBuilder setUser(AppUser user) {
        this.user = user;
        return this;
    }

    public OrderBuilder setStatus(OrderStatus status) {
        this.status = status;
        return this;
    }

    public OrderBuilder setShippingAddress(Address shippingAddress) {
        this.shippingAddress = shippingAddress;
        return this;
    }

    public OrderBuilder setOrderedItems(List<OrderedItem> orderedItems) {
        this.orderedItems = orderedItems;
        return this;
    }

    public Order build(){
        Date now = new DateTimeHelper().now();
        Order order = new Order();
        order.setCreatedAt(now);
        order.setUpdatedAt(now);
        order.setShippingAddress(shippingAddress);
        order.setUser(user);
        order.setStatus(status);
        orderedItems.forEach(item->{
            item.setOrder(order);
        });
        order.setOrderedItems(orderedItems);
        return order;
    }

}
