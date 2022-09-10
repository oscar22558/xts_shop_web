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

    public CreatePaymentIntentServiceImp(CreateOrderService createOrderService, OrderTotalCalculator orderTotalCalculator) {
        this.createOrderService = createOrderService;
        this.orderTotalCalculator = orderTotalCalculator;
    }

    public CreatePaymentIntentResponse createIntent(CreatePaymentIntentForm form){
        this.form = form;
        buildInvoice();
        PaymentIntent paymentIntent = buildPaymentIntent();
        CreateOrderRequest createOrderRequest = new CreateOrderRequest(
                form.getItemQuantities(),
                form.getUserAddressId(),
                paymentIntent.getId(),
                invoice
        );
        createOrderService.create(createOrderRequest);

        String clientSecret = paymentIntent.getClientSecret();
        return new CreatePaymentIntentResponse(clientSecret, invoice.getTotal());
    }

    private void buildInvoice(){
        orderTotalCalculator.setItemQuantities(form.getItemQuantities());
        invoice = orderTotalCalculator.getInvoice();
    }

    private PaymentIntent buildPaymentIntent(){
        Stripe.apiKey = StripeApiSecret.API_KEY;
        long amount = (long)(invoice.getTotal() * 100);
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
        try{
            return PaymentIntent.create(params);
        }catch (Exception ex){
            throw new RuntimeException(ex);
        }
    }

}
