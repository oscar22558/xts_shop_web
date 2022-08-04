package com.xtsshop.app.controller.users.payment;

import com.xtsshop.app.controller.users.payment.models.CancelPaymentIntentRequest;

public interface CancelPaymentIntentService {
    void execute(CancelPaymentIntentRequest request);
}
