package com.xtsshop.app.features.orders.models;

import com.xtsshop.app.AbstractRepresentationModel;
import com.xtsshop.app.db.entities.OrderedItem;

public class OrderedItemRepresentationModel implements AbstractRepresentationModel {
    private OrderedItem orderedItemEntity;

    public OrderedItemRepresentationModel(OrderedItem orderedItemEntity) {
        this.orderedItemEntity = orderedItemEntity;
    }

    public String getDescription(){
        return orderedItemEntity.getItem().getDescription();
    }

    public long getItemId(){
        return orderedItemEntity.getItem().getId();
    }

    public long getOrderedItemId(){
        return orderedItemEntity.getId();
    }

    public int getQuantity(){
        return orderedItemEntity.getQuantity();
    }

    public String getName(){
        return orderedItemEntity.getItem().getName();
    }

    public float getPrice(){
        return orderedItemEntity.getPrice();
    }

    public String getImgUrl(){
        return orderedItemEntity.getItem().getImage().getUri();
    }
}