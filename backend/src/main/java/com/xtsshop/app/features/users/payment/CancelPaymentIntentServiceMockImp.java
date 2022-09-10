package com.xtsshop.app.features.users.payment;

import com.xtsshop.app.features.users.payment.models.CancelPaymentIntentRequest;
import org.springframework.stereotype.Component;

import static com.xtsshop.app.features.users.payment.models.FakePaymentDetail.PAYMENT_INTENT_ID;

@Component
public class CancelPaymentIntentServiceMockImp implements CancelPaymentIntentService{
    private UserPaymentRepo userPaymentRepo;

    public CancelPaymentIntentServiceMockImp(UserPaymentRepo userPaymentRepo) {
        this.userPaymentRepo = userPaymentRepo;
    }

    @Override
    public void execute(CancelPaymentIntentRequest request) {
        deleteOrder(PAYMENT_INTENT_ID);
    }

    private void deleteOrder(String paymentIntentId){
        userPaymentRepo.deleteOrderByPaymentIntentId(paymentIntentId);
    }
}
