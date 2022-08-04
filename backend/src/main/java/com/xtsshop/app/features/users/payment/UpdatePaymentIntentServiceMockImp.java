package com.xtsshop.app.features.users.payment;

import org.springframework.stereotype.Service;

import com.xtsshop.app.features.users.payment.models.UpdateOrderRequest;
import com.xtsshop.app.features.users.payment.models.UpdatePaymentIntentForm;

import static com.xtsshop.app.features.users.payment.models.FakePaymentDetail.PAYMENT_INTENT_ID;

@Service
public class UpdatePaymentIntentServiceMockImp implements UpdatePaymentIntentService{
    private UpdateOrderService updateOrderService;

    public UpdatePaymentIntentServiceMockImp(UpdateOrderService updateOrderService) {
        this.updateOrderService = updateOrderService;
    }

    public void execute(UpdatePaymentIntentForm form){
        UpdateOrderRequest request = new UpdateOrderRequest();
        request.setItemQuantities(form.getItemQuantities());
        request.setAddressId(form.getUserAddressId());
        request.setPaymentIntentId(PAYMENT_INTENT_ID);
        updateOrderService.update(request);
    }
}
