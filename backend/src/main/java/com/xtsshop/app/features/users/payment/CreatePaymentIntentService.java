package com.xtsshop.app.features.users.payment;

import com.xtsshop.app.features.users.payment.models.CreatePaymentIntentForm;

public interface CreatePaymentIntentService {
    String createIntent(CreatePaymentIntentForm form);
}
