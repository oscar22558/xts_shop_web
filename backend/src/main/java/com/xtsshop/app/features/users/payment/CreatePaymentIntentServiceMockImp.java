package com.xtsshop.app.features.users.payment;

import com.xtsshop.app.db.entities.Invoice;
import com.xtsshop.app.features.users.payment.models.CreateOrderRequest;
import com.xtsshop.app.features.users.payment.models.CreatePaymentIntentForm;

import com.xtsshop.app.features.users.payment.models.FakePaymentDetail;
import org.springframework.stereotype.Service;

@Service
public class CreatePaymentIntentServiceMockImp implements CreatePaymentIntentService {
    private CreateOrderService createOrderService;
    private OrderTotalCalculator orderTotalCalculator;
    private CreatePaymentIntentForm form;
    private Invoice invoice;

    public CreatePaymentIntentServiceMockImp(CreateOrderService createOrderService, OrderTotalCalculator orderTotalCalculator) {
        this.createOrderService = createOrderService;
        this.orderTotalCalculator = orderTotalCalculator;
    }

    public String createIntent(CreatePaymentIntentForm form){
        this.form = form;
        buildInvoice();
        CreateOrderRequest createOrderRequest = new CreateOrderRequest(
                form.getItemQuantities(),
                form.getUserAddressId(),
                FakePaymentDetail.PAYMENT_INTENT_ID,
                invoice
        );
        createOrderService.create(createOrderRequest);
        return FakePaymentDetail.CLIENT_SECRET;
    }

    private void buildInvoice(){
        orderTotalCalculator.setItemQuantities(form.getItemQuantities());
        invoice = orderTotalCalculator.getInvoice();
    }
}
