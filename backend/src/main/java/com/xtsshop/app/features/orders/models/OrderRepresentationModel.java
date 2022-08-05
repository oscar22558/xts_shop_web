package com.xtsshop.app.features.orders.models;

import com.xtsshop.app.AbstractRepresentationModel;
import com.xtsshop.app.db.entities.Order;

import java.util.Collection;
import java.util.stream.Collectors;

public class OrderRepresentationModel implements AbstractRepresentationModel {
    private Order entity;

    public OrderRepresentationModel(Order entity){
        this.entity = entity;
    }

    public long getId(){
        return entity.getId();
    }

    public ShippingAddressRepresentationModel getShippingAddress(){
        return new ShippingAddressRepresentationModel(entity.getShippingAddress());
    }

    public String getOrderStatus(){
        return entity.getStatus().name();
    }

    public Collection<OrderedItemRepresentationModel> getItems(){
        return entity.getOrderedItems()
                .stream()
                .map(OrderedItemRepresentationModel::new)
                .collect(Collectors.toList());
    }
}
