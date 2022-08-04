package com.xtsshop.app.controller.users.payment;

import com.xtsshop.app.controller.users.payment.models.CreatePaymentIntentForm;

public interface CreatePaymentIntentService {
    String createIntent(CreatePaymentIntentForm form);
}
