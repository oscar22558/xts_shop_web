package com.xtsshop.app.features.users.payment;

import com.xtsshop.app.db.entities.Invoice;
import com.xtsshop.app.features.users.payment.models.CreatePaymentIntentResponse;
import org.springframework.stereotype.Service;

import com.stripe.Stripe;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;

import com.xtsshop.app.features.users.payment.models.CreateOrderRequest;
import com.xtsshop.app.features.users.payment.models.CreatePaymentIntentForm;
import com.xtsshop.app.features.users.payment.models.StripeApiSecret;


@Service
public class CreatePaymentIntentServiceImp implements CreatePaymentIntentService {
    private CreateOrderService createOrderService;
    private OrderTotalCalculator orderTotalCalculator;
    private CreatePaymentIntentForm form;
    private Invoice invoice;
    private PaymentIntent paymentIntent;
    private StripeApiSecret stripeApiSecret;

    public CreatePaymentIntentServiceImp(CreateOrderService createOrderService, OrderTotalCalculator orderTotalCalculator, StripeApiSecret stripeApiSecret) {
        this.createOrderService = createOrderService;
        this.orderTotalCalculator = orderTotalCalculator;
        this.stripeApiSecret = stripeApiSecret;
    }

    public CreatePaymentIntentResponse createIntent(CreatePaymentIntentForm form){
        this.form = form;
        buildInvoice();
        paymentIntent = buildPaymentIntent();
        CreateOrderRequest createOrderRequest = buildCreateOrderRequest();
        createOrderService.create(createOrderRequest);

        String clientSecret = paymentIntent.getClientSecret();
        return new CreatePaymentIntentResponse(clientSecret, invoice.getTotal());
    }

    private void buildInvoice(){
        orderTotalCalculator.setItemQuantities(form.getItemQuantities());
        invoice = orderTotalCalculator.getInvoice();
    }

    private PaymentIntent buildPaymentIntent() {
        Stripe.apiKey = stripeApiSecret.API_KEY;
        long amount = (long) (invoice.getTotal() * 100);
        PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
                .setAmount(amount)
                .setCurrency("HKD")
                .setAutomaticPaymentMethods(
                        PaymentIntentCreateParams.AutomaticPaymentMethods
                                .builder()
                                .setEnabled(true)
                                .build()
                )
                .build();
        try {
            return PaymentIntent.create(params);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    private CreateOrderRequest buildCreateOrderRequest(){
        CreateOrderRequest request = new CreateOrderRequest();
        request.setItemQuantities(form.getItemQuantities());
        request.setPaymentIntentId(paymentIntent.getId());
        request.setInvoice(invoice);
        request.setRecipientFirstName(form.getRecipientFirstName());
        request.setRecipientLastName(form.getRecipientLastName());
        request.setRecipientEmail(form.getRecipientEmail());
        request.setRecipientPhone(form.getRecipientPhone());
        request.setCountry(form.getCountry());
        request.setCity(form.getCity());
        request.setArea(form.getArea());
        request.setDistrict(form.getDistrict());
        request.setRow1(form.getRow1());
        request.setRow2(form.getRow2());
        return request;
    }
}
