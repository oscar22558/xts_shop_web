package com.xtsshop.app.controller.users.payment;

import org.springframework.stereotype.Service;

import com.stripe.Stripe;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;

import com.xtsshop.app.controller.users.payment.models.CreateOrderRequest;
import com.xtsshop.app.controller.users.payment.models.CreatePaymentIntentForm;
import com.xtsshop.app.controller.users.payment.models.StripeApiSecret;


@Service
public class CreatePaymentIntentServiceImp implements CreatePaymentIntentService {
    private CreateOrderService createOrderService;
    private OrderAmountCalculator orderAmountCalculator;
    private CreatePaymentIntentForm form;

    public CreatePaymentIntentServiceImp(CreateOrderService createOrderService, OrderAmountCalculator orderAmountCalculator) {
        this.createOrderService = createOrderService;
        this.orderAmountCalculator = orderAmountCalculator;
    }

    public String createIntent(CreatePaymentIntentForm form){
        this.form = form;
        PaymentIntent paymentIntent = buildPaymentIntent();
        CreateOrderRequest createOrderRequest = new CreateOrderRequest(
                form.getItemQuantities(),
                form.getUserAddressId(),
                paymentIntent.getId()
        );
        createOrderService.create(createOrderRequest);
        return paymentIntent.getClientSecret();
    }

    private PaymentIntent buildPaymentIntent(){
        Stripe.apiKey = StripeApiSecret.API_KEY;
        PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
                .setAmount(orderAmountCalculator.calculate(form.getItemQuantities()))
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
