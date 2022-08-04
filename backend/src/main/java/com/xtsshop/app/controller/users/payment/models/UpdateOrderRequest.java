package com.xtsshop.app.controller.users.payment.models;

import com.stripe.model.PaymentIntent;
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
