package com.xtsshop.app.features.users.payment.models;

import com.xtsshop.app.db.entities.Invoice;
import lombok.Getter;

import java.util.List;

@Getter
public class CreateOrderRequest {
    private List<ItemQuantity> itemQuantities;
    private long userAddressId;
    private String paymentIntentId;
    private Invoice invoice;

    public CreateOrderRequest(List<ItemQuantity> itemQuantities, long userAddressId, String paymentIntentId, Invoice invoice) {
        this.itemQuantities = itemQuantities;
        this.userAddressId = userAddressId;
        this.paymentIntentId = paymentIntentId;
        this.invoice = invoice;
    }
}