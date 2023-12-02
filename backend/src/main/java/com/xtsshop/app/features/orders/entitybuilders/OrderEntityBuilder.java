package com.xtsshop.app.features.orders.entitybuilders;

import com.xtsshop.app.db.entities.*;
import com.xtsshop.app.helpers.DateTimeHelper;

import java.sql.Date;
import java.util.List;

public class OrderEntityBuilder {

    private ShippingAddress shippingAddress;
    private AppUser user;
    private OrderStatus status;
    private List<OrderedItem> orderedItems;

    public OrderEntityBuilder setUser(AppUser user) {
        this.user = user;
        return this;
    }

    public OrderEntityBuilder setStatus(OrderStatus status) {
        this.status = status;
        return this;
    }

    public OrderEntityBuilder setShippingAddress(ShippingAddress shippingAddress) {
        this.shippingAddress = shippingAddress;
        return this;
    }

    public OrderEntityBuilder setOrderedItems(List<OrderedItem> orderedItems) {
        this.orderedItems = orderedItems;
        return this;
    }

    public Order build(){
        Date now = new DateTimeHelper().now();
        Order order = new Order();
        order.setCreatedAt(now);
        order.setUpdatedAt(now);

        shippingAddress.setOrder(order);
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
