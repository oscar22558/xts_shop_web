package com.xtsshop.app.features.users.payment;

import com.xtsshop.app.features.users.payment.models.CreatePaymentIntentForm;
import com.xtsshop.app.features.users.payment.models.CreatePaymentIntentResponse;

public interface CreatePaymentIntentService {
    CreatePaymentIntentResponse createIntent(CreatePaymentIntentForm form);
}
