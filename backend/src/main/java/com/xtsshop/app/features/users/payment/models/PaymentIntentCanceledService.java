package com.xtsshop.app.features.users.payment.models;

import com.stripe.model.PaymentIntent;
import com.stripe.model.StripeObject;
import com.xtsshop.app.advices.exception.RecordNotFoundException;
import com.xtsshop.app.features.users.payment.UserPaymentRepo;
import com.xtsshop.app.db.entities.Order;
import com.xtsshop.app.db.entities.OrderStatus;
import org.springframework.stereotype.Service;

@Service
public class PaymentIntentCanceledService {
    private UserPaymentRepo userPaymentRepo;

    public void handleEvent(StripeObject stripeObject){
        PaymentIntent paymentIntent = (PaymentIntent) stripeObject;
        Order order = userPaymentRepo.findOrderByUserPaymentIntent(paymentIntent)
                .orElseThrow(()-> new RecordNotFoundException("Order with payment id " + paymentIntent.getId() + " not found."));
        order.setStatus(OrderStatus.CANCELED);
        userPaymentRepo.saveOrder(order);
        sendEmailToConfirmCancelAndRefund();
    }

    private void sendEmailToConfirmCancelAndRefund(){

    }
}
