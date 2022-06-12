package com.xtsshop.app.controller.orders;

import com.xtsshop.app.db.entities.payment.PaymentType;
import com.xtsshop.app.form.orders.PaymentCreateForm;
import org.springframework.stereotype.Component;

@Component("app.http.orders.PayTestUtil")
public class PayTestUtil {
    public String getRoute(String baseUrl){
        return baseUrl+"/pay";
    }
    public PaymentCreateForm buildPaymentCreateForm(){
        PaymentCreateForm form = new PaymentCreateForm();
        form.setPaymentType(PaymentType.CREDIT_CARD);
        form.setCardNum("213908531241232");
        form.setHolderName("Mario A");
        form.setBankCode("0232");
        form.setPaidTotal(739.3f);
        return form;
    }
}
