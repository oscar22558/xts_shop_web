package com.xtsshop.app.features.users.cart.models;

import com.xtsshop.app.features.orders.models.OrderedItemCreateRequest;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Setter
@Getter
public class OrderedItemCreateForm {
    @NotNull
    @Min(0)
    private Long itemId;
    @NotNull
    @Min(0)
    private Integer quantity;
    public OrderedItemCreateRequest toRequest(){
        OrderedItemCreateRequest orderItemRequest = new OrderedItemCreateRequest();
        orderItemRequest.setItemId(itemId);
        orderItemRequest.setQuantity(quantity);
        return orderItemRequest;
    }
}
