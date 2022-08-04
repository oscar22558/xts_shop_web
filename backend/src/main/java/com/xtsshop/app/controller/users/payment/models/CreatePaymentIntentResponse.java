package com.xtsshop.app.controller.users.payment.models;

import lombok.Getter;

@Getter
public class CreatePaymentIntentResponse {
    private String clientSecret;

    public CreatePaymentIntentResponse(String clientSecret) {
        this.clientSecret = clientSecret;
    }
}
