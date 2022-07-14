package com.xtsshop.app.controller.orders.models;

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
    private float paidPrice;

    public OrderedItemRepresentationModel(OrderedItem orderedItemEntity){
        this.itemId = orderedItemEntity.getItem().getId();
        this.orderedItemId = orderedItemEntity.getId();
        this.quantity = orderedItemEntity.getQuantity();
        this.name = orderedItemEntity.getItem().getName();
        this.paidPrice = orderedItemEntity.getOrderPrice().getValue();
    }

}