package com.xtsshop.app.features.orders.models;

import com.xtsshop.app.AbstractRepresentationModel;
import com.xtsshop.app.db.entities.OrderedItem;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderedItemRepresentationModel implements AbstractRepresentationModel {
    private long itemId;
    private long orderedItemId;
    private int quantity;
    private String name;
    private float price;

    public OrderedItemRepresentationModel(OrderedItem orderedItemEntity){
        this.itemId = orderedItemEntity.getItem().getId();
        this.orderedItemId = orderedItemEntity.getId();
        this.quantity = orderedItemEntity.getQuantity();
        this.name = orderedItemEntity.getItem().getName();
        this.price = orderedItemEntity.getOrderPriceValue();
    }

}