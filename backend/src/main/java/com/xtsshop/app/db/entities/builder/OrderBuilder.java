package com.xtsshop.app.db.entities.builder;

import com.xtsshop.app.db.entities.*;
import com.xtsshop.app.db.entities.payment.Payment;

import java.util.List;
import java.util.Set;

public class OrderBuilder {

    private Address shippingAddress;
    private Payment payment;
    private AppUser user;
    private OrderStatus status;
    private List<OrderedItem> orderedItems;
    public OrderBuilder setPayment(Payment payment) {
        this.payment = payment;
        return this;
    }

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
        Order order = new Order();
        order.setShippingAddress(shippingAddress);
        order.setPayment(payment);
        order.setUser(user);
        order.setStatus(status);
        order.setOrderedItems(orderedItems);
        return order;
    }

}
