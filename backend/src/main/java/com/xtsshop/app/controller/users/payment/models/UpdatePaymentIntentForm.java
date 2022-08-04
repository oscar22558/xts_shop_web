package com.xtsshop.app.controller.users.payment.models;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Setter
@Getter
public class UpdatePaymentIntentForm extends CreatePaymentIntentForm{
    @NotNull
    private String clientSecret;
}
