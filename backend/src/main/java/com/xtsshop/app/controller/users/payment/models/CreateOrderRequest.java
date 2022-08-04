package com.xtsshop.app.controller.users.payment.models;

import lombok.Getter;

import java.util.List;

@Getter
public class CreateOrderRequest {
    private List<ItemQuantity> itemQuantities;
    private long userAddressId;
    private String paymentIntentId;

    public CreateOrderRequest(List<ItemQuantity> itemQuantities, long userAddressId, String paymentIntentId) {
        this.itemQuantities = itemQuantities;
        this.userAddressId = userAddressId;
        this.paymentIntentId = paymentIntentId;
    }
}