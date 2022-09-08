package com.xtsshop.app.features.users.payment.models;

import lombok.Getter;

@Getter
public class CreatePaymentIntentResponse {
    private String clientSecret;
    private Float orderTotal;

    public CreatePaymentIntentResponse(String clientSecret, Float orderTotal) {
        this.clientSecret = clientSecret;
        this.orderTotal = orderTotal;
    }
}
