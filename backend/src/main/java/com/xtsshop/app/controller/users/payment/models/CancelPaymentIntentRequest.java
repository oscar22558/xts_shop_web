package com.xtsshop.app.controller.users.payment.models;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
public class CancelPaymentIntentRequest {
    @NotBlank
    private String paymentIntentId;
}
