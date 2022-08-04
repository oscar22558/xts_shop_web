package com.xtsshop.app.controller.users.payment;

import com.xtsshop.app.controller.users.payment.models.PostPaymentEvent;

public interface PostPaymentEventService {
    void handle(PostPaymentEvent postPaymentEvent);
}
