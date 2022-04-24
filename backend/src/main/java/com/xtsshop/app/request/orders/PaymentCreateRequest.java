package com.xtsshop.app.request.orders;

import com.xtsshop.app.db.entities.payment.Payment;
import com.xtsshop.app.db.entities.payment.PaymentType;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PaymentCreateRequest {
    private float paidTotal;
    private PaymentType paymentType;
    private String cardNum;
    private String holderName;
    private String bankCode;
    private float orderItemPriceTotal;
    public Payment toEntity(){
        Payment payment = new Payment();
        payment.setPaymentType(paymentType);
        payment.setCardNum(cardNum);
        payment.setHolderName(holderName);
        payment.setBankCode(bankCode);
        return payment;
    }
}
