package com.xtsshop.app.features.users.payment;

import com.xtsshop.app.features.users.payment.models.PostPaymentEvent;

public interface PostPaymentEventService {
    void handle(PostPaymentEvent postPaymentEvent);
}
