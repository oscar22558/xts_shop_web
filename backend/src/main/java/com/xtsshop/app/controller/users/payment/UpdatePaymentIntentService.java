package com.xtsshop.app.controller.users.payment;

import com.xtsshop.app.controller.users.payment.models.UpdatePaymentIntentForm;

public interface UpdatePaymentIntentService {
    void execute(UpdatePaymentIntentForm form);
}
