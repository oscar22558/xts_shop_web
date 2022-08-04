package com.xtsshop.app.controller.users.payment;

import com.stripe.model.PaymentIntent;
import com.xtsshop.app.controller.users.payment.models.CancelPaymentIntentRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CancelPaymentIntentServiceImp implements CancelPaymentIntentService{
    private static final Logger logger = LoggerFactory.getLogger(CancelPaymentIntentServiceImp.class);

    private UserPaymentRepo userPaymentRepo;
    private List<String> statusCanCancelPaymentIntent = List.of(
            "requires_payment_method",
            "requires_capture",
            "requires_confirmation",
            "requires_action",
            "processing"
    );

    public CancelPaymentIntentServiceImp(UserPaymentRepo userPaymentRepo) {
        this.userPaymentRepo = userPaymentRepo;
    }

    public void execute(CancelPaymentIntentRequest request){
        try{
            PaymentIntent paymentIntent = PaymentIntent.retrieve(request.getPaymentIntentId());
            String intentCurrentStatus = paymentIntent.getStatus();
            if(isNotPaymentIntentCancelable(intentCurrentStatus)){
                logger.warn("⚠️  Payment intent" + paymentIntent.getId() + " is no longer cancelable.");
                return;
            }
            paymentIntent.cancel();
            deleteOrder(paymentIntent.getId());
        }catch (Exception ex){
            throw new RuntimeException(ex);
        }
    }

    private boolean isNotPaymentIntentCancelable(String intentCurrentStatus){
        return statusCanCancelPaymentIntent.stream()
                .filter(status->status.equals(intentCurrentStatus))
                .findFirst()
                .isEmpty();
    }

    private void deleteOrder(String paymentIntentId){
        userPaymentRepo.deleteOrderByPaymentIntentId(paymentIntentId);
    }
}
