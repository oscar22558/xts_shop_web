package com.xtsshop.app.features.users.payment;

import com.stripe.Stripe;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentUpdateParams;
import com.xtsshop.app.features.users.payment.models.StripeApiSecret;
import com.xtsshop.app.features.users.payment.models.UpdateOrderRequest;
import com.xtsshop.app.features.users.payment.models.UpdatePaymentIntentForm;
import org.springframework.stereotype.Service;

@Service
public class UpdatePaymentIntentServiceImp implements UpdatePaymentIntentService{

    private OrderAmountCalculator orderAmountCalculator;
    private UpdateOrderService updateOrderService;
    private UpdatePaymentIntentForm form;

    public UpdatePaymentIntentServiceImp(OrderAmountCalculator orderAmountCalculator, UpdateOrderService updateOrderService) {
        this.orderAmountCalculator = orderAmountCalculator;
        this.updateOrderService = updateOrderService;
    }

    public void execute(UpdatePaymentIntentForm form){
        this.form = form;
        PaymentIntent paymentIntent = tryToUpdateIntent();
        UpdateOrderRequest request = new UpdateOrderRequest();
        request.setItemQuantities(form.getItemQuantities());
        request.setAddressId(form.getUserAddressId());
        request.setPaymentIntentId(paymentIntent.getId());
        updateOrderService.update(request);
    }

    private PaymentIntent tryToUpdateIntent(){
        Stripe.apiKey = StripeApiSecret.API_KEY;
        long newAmount = orderAmountCalculator.calculate(form.getItemQuantities());
        PaymentIntent paymentIntent;
        try {
            paymentIntent = PaymentIntent.retrieve(form.getClientSecret());
            PaymentIntentUpdateParams params = PaymentIntentUpdateParams.builder()
                    .setAmount(newAmount)
                    .build();
            paymentIntent.update(params);
            return paymentIntent;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
