package com.xtsshop.app.features.users.payment;

import com.stripe.Stripe;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentUpdateParams;
import com.xtsshop.app.db.entities.Invoice;
import com.xtsshop.app.features.users.payment.models.StripeApiSecret;
import com.xtsshop.app.features.users.payment.models.UpdateOrderRequest;
import com.xtsshop.app.features.users.payment.models.UpdatePaymentIntentForm;
import org.springframework.stereotype.Service;

@Service
public class UpdatePaymentIntentServiceImp implements UpdatePaymentIntentService{

    private OrderTotalCalculator orderAmountCalculator;
    private UpdateOrderService updateOrderService;
    private UpdatePaymentIntentForm form;
    private Invoice newInvoice;
    private StripeApiSecret stripeApiSecret;

    public UpdatePaymentIntentServiceImp(OrderTotalCalculator orderAmountCalculator, UpdateOrderService updateOrderService, StripeApiSecret stripeApiSecret) {
        this.orderAmountCalculator = orderAmountCalculator;
        this.updateOrderService = updateOrderService;
        this.stripeApiSecret = stripeApiSecret;
    }

    public void execute(UpdatePaymentIntentForm form){
        this.form = form;
        buildNewInvoice();
        PaymentIntent paymentIntent = tryToUpdateIntent();
        UpdateOrderRequest request = new UpdateOrderRequest();
        request.setItemQuantities(form.getItemQuantities());
        request.setPaymentIntentId(paymentIntent.getId());
        request.setInvoice(newInvoice);
        request.setCountry(form.getCountry());
        request.setCity(form.getCity());
        request.setArea(form.getArea());
        request.setDistrict(form.getDistrict());
        request.setRow1(form.getRow1());
        request.setRow2(form.getRow2());
        updateOrderService.update(request);
    }

    private void buildNewInvoice(){
        orderAmountCalculator.setItemQuantities(form.getItemQuantities());
        newInvoice = orderAmountCalculator.getInvoice();
    }

    private PaymentIntent tryToUpdateIntent(){
        Stripe.apiKey = stripeApiSecret.API_KEY;
        long newAmount = (long)(newInvoice.getTotal() * 100);
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
