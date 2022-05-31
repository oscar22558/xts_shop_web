package com.xtsshop.app.domain.request.orders;

import com.xtsshop.app.db.entities.payment.Payment;
import com.xtsshop.app.db.entities.payment.PaymentType;
import com.xtsshop.app.util.DateTimeUtil;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

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
        Date now = new DateTimeUtil().now();
        Payment payment = new Payment();
        payment.setCreatedAt(now);
        payment.setUpdatedAt(now);
        payment.setPaymentType(paymentType);
        payment.setCardNum(cardNum);
        payment.setHolderName(holderName);
        payment.setBankCode(bankCode);
        return payment;
    }
}
