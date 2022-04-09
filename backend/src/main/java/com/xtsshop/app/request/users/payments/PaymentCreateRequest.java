package com.xtsshop.app.request.users.payments;

import com.xtsshop.app.db.entities.payment.Payment;
import com.xtsshop.app.db.entities.payment.PaymentType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

@Setter
@Getter
public class PaymentCreateRequest {
    private PaymentType paymentType;
    private String cardNum;
    private String holderName;
    private String bankCode;
    public Payment toEntity(){
        Payment payment = new Payment();
        payment.setPaymentType(paymentType);
        payment.setCardNum(cardNum);
        payment.setHolderName(holderName);
        payment.setBankCode(bankCode);
        return payment;
    }
}
