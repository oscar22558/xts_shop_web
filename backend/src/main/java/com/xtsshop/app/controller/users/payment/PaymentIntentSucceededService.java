package com.xtsshop.app.controller.users.payment;

import com.xtsshop.app.advices.exception.RecordNotFoundException;
import com.xtsshop.app.db.entities.Order;
import com.xtsshop.app.db.entities.OrderStatus;
import org.springframework.stereotype.Service;

@Service
public class PaymentIntentSucceededService {

    private UserPaymentRepo userPaymentRepo;

    public PaymentIntentSucceededService(UserPaymentRepo userPaymentRepo) {
        this.userPaymentRepo = userPaymentRepo;
    }

    public void handleEvent(String paymentIntentId){
        Order order = userPaymentRepo.findOrderByUserPaymentIntentId(paymentIntentId)
                .orElseThrow(()-> new RecordNotFoundException("Order with payment id " + paymentIntentId + " not found."));
        order.setStatus(OrderStatus.PAID);
        userPaymentRepo.saveOrder(order);
        sendEmailToConfirmOrderAndPayment();
    }

    private void sendEmailToConfirmOrderAndPayment(){
    }
}
