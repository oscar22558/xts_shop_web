package com.xtsshop.app.features.users.payment;

import com.xtsshop.app.features.users.payment.models.CancelPaymentIntentRequest;

public interface CancelPaymentIntentService {
    void execute(CancelPaymentIntentRequest request);
}
