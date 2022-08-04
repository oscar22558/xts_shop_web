package com.xtsshop.app.controller.users.payment.models;

import java.util.Optional;

import com.stripe.model.Event;
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
