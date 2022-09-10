package com.xtsshop.app.features.orders.entitybuilders;

import com.xtsshop.app.db.entities.*;
import com.xtsshop.app.helpers.DateTimeHelper;

import java.sql.Date;
import java.util.List;

public class OrderEntityBuilder {

    private Address shippingAddress;
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

    public OrderEntityBuilder setShippingAddress(Address shippingAddress) {
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

        ShippingAddress shippingAddressEntity = new ShippingAddressEntityBuilder(this.shippingAddress).build();
        shippingAddressEntity.setOrder(order);
        order.setShippingAddress(shippingAddressEntity);

        order.setUser(user);
        order.setStatus(status);

        orderedItems.forEach(item->{
            item.setOrder(order);
        });
        order.setOrderedItems(orderedItems);

        return order;
    }

}
