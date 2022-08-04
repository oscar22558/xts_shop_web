package com.xtsshop.app.controller.users.payment.models;

import com.xtsshop.app.db.entities.Order;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UpdateOrderedItemsRequest {
    private Order order;
    private List<ItemQuantity> itemQuantities;
}
