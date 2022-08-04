package com.xtsshop.app.features.users.payment.models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UpdateOrderRequest {
    private List<ItemQuantity> itemQuantities;
    private long addressId;
    private String paymentIntentId;
}
