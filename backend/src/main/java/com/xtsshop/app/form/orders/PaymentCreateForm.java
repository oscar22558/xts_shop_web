package com.xtsshop.app.form.orders;

import com.xtsshop.app.db.entities.payment.Payment;
import com.xtsshop.app.db.entities.payment.PaymentType;
import com.xtsshop.app.request.users.payments.PaymentCreateRequest;

public class PaymentCreateForm {
    private PaymentType paymentType;
    private String cardNum;
    private String holderName;
    private String bankCode;
    public PaymentCreateRequest toRequest(){
        PaymentCreateRequest request = new PaymentCreateRequest();
        request.setPaymentType(paymentType);
        request.setCardNum(cardNum);
        request.setHolderName(holderName);
        request.setBankCode(bankCode);
        return request;
    }

}
