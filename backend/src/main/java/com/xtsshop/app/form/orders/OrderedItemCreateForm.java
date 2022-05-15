package com.xtsshop.app.form.orders;

import com.xtsshop.app.request.orders.OrderedItemCreateRequest;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Setter
@Getter
public class OrderedItemCreateForm {
    @NotNull
    @Min(0)
    Long itemId;
    @NotNull
    @Min(0)
    Integer quantity;
    public OrderedItemCreateRequest toRequest(){
        OrderedItemCreateRequest orderItemRequest = new OrderedItemCreateRequest();
        orderItemRequest.setItemId(itemId);
        orderItemRequest.setQuantity(quantity);
        return orderItemRequest;
    }
}
