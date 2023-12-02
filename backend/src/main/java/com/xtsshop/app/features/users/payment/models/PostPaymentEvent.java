package com.xtsshop.app.features.users.payment.models;

import java.util.Optional;

import lombok.Getter;

@Getter
public class PostPaymentEvent {
    String eventJsonString;
    Optional<String> stripeSignatureHeader;

    public PostPaymentEvent(String eventJsonString, String stripeSignatureHeader) {
        this.eventJsonString = eventJsonString;
        this.stripeSignatureHeader = Optional.ofNullable(stripeSignatureHeader);
    }
}
