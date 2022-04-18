package com.xtsshop.app.viewmodel;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
public class OrderedItemModel implements AbstractViewModel {
    private ItemModel item;
    private Integer quantity;

    public OrderedItemModel(ItemModel item, Integer quantity) {
        this.item = item;
        this.quantity = quantity;
    }
}