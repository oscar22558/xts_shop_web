package com.xtsshop.app.features.orders;

import com.xtsshop.app.advices.exception.RecordNotFoundException;
import com.xtsshop.app.db.entities.Order;
import com.xtsshop.app.db.entities.payment.PaymentType;
import com.xtsshop.app.features.users.cart.models.PaymentCreateForm;
import com.xtsshop.app.db.seed.DevelopmentDataSeed;
import org.springframework.stereotype.Component;

@Component
public class PayOrderTestHelper {

    private DevelopmentDataSeed dataSeed;
    private OrderTestHelper orderTestHelper;

    public PayOrderTestHelper(DevelopmentDataSeed dataSeed, OrderTestHelper orderTestHelper) {
        this.dataSeed = dataSeed;
        this.orderTestHelper = orderTestHelper;
    }

    public String getRoute(){
        return "/api/orders/{orderId}/pay";
    }

    public PaymentCreateForm buildPaymentCreateForm(){
        PaymentCreateForm form = new PaymentCreateForm();
        form.setPaymentType(PaymentType.CREDIT_CARD);
        form.setPaidTotal(732.4f);
        return form;
    }

    public void insertData(){
        dataSeed.insertData();
    }

    public void insertOrderForNewUser(){
        orderTestHelper.insertDataWithNewUserOrder();
    }

    public long getOrderIdToPay() {
        return getOrderToPay().getId();
    }

    public Order getOrderToPay(){
        return orderTestHelper
                .getLatestOrder()
                .orElseThrow(()-> new RecordNotFoundException("No order to pay."));
    }
}
