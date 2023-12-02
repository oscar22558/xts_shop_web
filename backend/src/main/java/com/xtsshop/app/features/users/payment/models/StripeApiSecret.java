package com.xtsshop.app.features.users.payment.models;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class StripeApiSecret {
    @Value("#{environment['STRIPE_API_KEY']}")
    public String API_KEY;
    @Value("#{environment['STRIPE_END_POINT_SECRET']}")
    public String END_POINT_SECRET;
}
