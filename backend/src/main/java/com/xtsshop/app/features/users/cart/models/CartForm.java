package com.xtsshop.app.features.users.cart.models;

import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;

@Setter
public class CartForm {
    @NotNull
    private List<@NotNull Long> itemIds;

    public CartRequest toRequest(){
        CartRequest request = new CartRequest();
        request.setItemIds(itemIds);
        return request;
    }
}
