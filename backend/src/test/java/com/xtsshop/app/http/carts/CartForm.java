package com.xtsshop.app.http.carts;

import lombok.Getter;

import java.util.List;

@Getter
public class CartForm {
    private List<Long> itemIds;

    public CartForm(List<Long> itemIds) {
        this.itemIds = itemIds;
    }
}
