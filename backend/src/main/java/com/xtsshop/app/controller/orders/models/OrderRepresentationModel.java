package com.xtsshop.app.controller.orders.models;

import com.xtsshop.app.AbstractRepresentationModel;
import com.xtsshop.app.db.entities.Order;

import java.util.Collection;
import java.util.stream.Collectors;

public class OrderRepresentationModel implements AbstractRepresentationModel {
    private Order entity;

    public OrderRepresentationModel(Order entity){
        this.entity = entity;
    }

    public ShippingAddressRepresentationModel getShippingAddress(){
        return new ShippingAddressRepresentationModel(entity.getShippingAddress());
    }

    public String getOrderStatus(){
        return entity.getStatus().name();
    }

    public Collection<OrderedItemRepresentationModel> getItems(){
        return getOrderItemViewModels();
    }

    private Collection<OrderedItemRepresentationModel> getOrderItemViewModels(){
        return entity.getOrderedItems()
                .stream()
                .map(OrderedItemRepresentationModel::new)
                .collect(Collectors.toList());
    }
}
