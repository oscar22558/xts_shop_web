package com.xtsshop.app.features.users.payment;

import com.xtsshop.app.db.entities.Invoice;
import com.xtsshop.app.features.users.payment.models.CreateOrderRequest;
import com.xtsshop.app.features.users.payment.models.CreatePaymentIntentForm;

import com.xtsshop.app.features.users.payment.models.CreatePaymentIntentResponse;
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

    public CreatePaymentIntentResponse createIntent(CreatePaymentIntentForm form){
        this.form = form;
        buildInvoice();
        CreateOrderRequest createOrderRequest = buildCreateOrderRequest();
        createOrderService.create(createOrderRequest);
        return new CreatePaymentIntentResponse(FakePaymentDetail.CLIENT_SECRET, invoice.getTotal());
    }

    private void buildInvoice(){
        orderTotalCalculator.setItemQuantities(form.getItemQuantities());
        invoice = orderTotalCalculator.getInvoice();
    }

    private CreateOrderRequest buildCreateOrderRequest(){
        CreateOrderRequest createOrderRequest = new CreateOrderRequest();
        createOrderRequest.setItemQuantities(form.getItemQuantities());
        createOrderRequest.setUserAddressId(form.getUserAddressId());
        createOrderRequest.setPaymentIntentId(FakePaymentDetail.PAYMENT_INTENT_ID);
        createOrderRequest.setInvoice(invoice);
        createOrderRequest.setRecipientEmail(form.getRecipientEmail());
        createOrderRequest.setRecipientPhone(form.getRecipientPhone());
        createOrderRequest.setRecipientEmail(form.getRecipientEmail());
        return createOrderRequest;
    }

}
