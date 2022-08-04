package com.xtsshop.app.features.users.payment;

import com.xtsshop.app.features.users.payment.models.CreateOrderRequest;
import com.xtsshop.app.features.users.payment.models.CreatePaymentIntentForm;

import com.xtsshop.app.features.users.payment.models.FakePaymentDetail;
import org.springframework.stereotype.Service;

@Service
public class CreatePaymentIntentServiceMockImp implements CreatePaymentIntentService {
    private CreateOrderService createOrderService;

    public CreatePaymentIntentServiceMockImp(CreateOrderService createOrderService) {
        this.createOrderService = createOrderService;
    }

    public String createIntent(CreatePaymentIntentForm form){
        CreateOrderRequest createOrderRequest = new CreateOrderRequest(
                form.getItemQuantities(),
                form.getUserAddressId(),
                FakePaymentDetail.PAYMENT_INTENT_ID
        );
        createOrderService.create(createOrderRequest);
        return FakePaymentDetail.CLIENT_SECRET;
    }

}
