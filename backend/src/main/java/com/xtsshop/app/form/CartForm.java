package com.xtsshop.app.form;

import com.xtsshop.app.domain.request.users.cart.CartRequest;
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
