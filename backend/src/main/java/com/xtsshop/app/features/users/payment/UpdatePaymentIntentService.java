package com.xtsshop.app.features.users.payment;

import com.xtsshop.app.features.users.payment.models.UpdatePaymentIntentForm;

public interface UpdatePaymentIntentService {
    void execute(UpdatePaymentIntentForm form);
}
