package com.xtsshop.app.features.users.payment;

import com.xtsshop.app.db.entities.Invoice;
import org.springframework.stereotype.Service;

import com.xtsshop.app.features.users.payment.models.UpdateOrderRequest;
import com.xtsshop.app.features.users.payment.models.UpdatePaymentIntentForm;

import static com.xtsshop.app.features.users.payment.models.FakePaymentDetail.PAYMENT_INTENT_ID;

@Service
public class UpdatePaymentIntentServiceMockImp implements UpdatePaymentIntentService{
    private UpdateOrderService updateOrderService;
    private OrderTotalCalculator orderTotalCalculator;
    private UpdatePaymentIntentForm form;
    private Invoice newInvoice;

    public UpdatePaymentIntentServiceMockImp(UpdateOrderService updateOrderService, OrderTotalCalculator orderTotalCalculator) {
        this.updateOrderService = updateOrderService;
        this.orderTotalCalculator = orderTotalCalculator;
    }

    public void execute(UpdatePaymentIntentForm form){
        this.form = form;
        buildNewInvoice();
        UpdateOrderRequest request = new UpdateOrderRequest();
        request.setItemQuantities(form.getItemQuantities());
        request.setAddressId(form.getUserAddressId());
        request.setPaymentIntentId(PAYMENT_INTENT_ID);
        request.setInvoice(newInvoice);
        updateOrderService.update(request);
    }

    private void buildNewInvoice(){
        orderTotalCalculator.setItemQuantities(form.getItemQuantities());
        newInvoice = orderTotalCalculator.getInvoice();
    }
}
