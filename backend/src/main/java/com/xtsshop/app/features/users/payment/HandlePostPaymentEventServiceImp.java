package com.xtsshop.app.features.users.payment;

import com.google.gson.JsonSyntaxException;
import com.stripe.exception.SignatureVerificationException;
import com.stripe.model.*;
import com.stripe.net.ApiResource;
import com.stripe.net.Webhook;
import com.xtsshop.app.features.users.payment.models.PaymentIntentCanceledService;
import com.xtsshop.app.features.users.payment.models.PostPaymentEvent;
import com.xtsshop.app.features.users.payment.models.StripeApiSecret;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HandlePostPaymentEventServiceImp implements PostPaymentEventService {
    private static final Logger logger = LoggerFactory.getLogger(HandlePostPaymentEventServiceImp.class);
    private String endpointSecret;
    private PaymentIntentSucceededService paymentIntentSucceededService;
    private PaymentIntentCanceledService paymentIntentCanceledService;

    private Event event;
    private StripeObject stripeObject;

    public HandlePostPaymentEventServiceImp(PaymentIntentSucceededService paymentIntentSucceededService, PaymentIntentCanceledService paymentIntentCanceledService, StripeApiSecret stripeApiSecret) {
        this.paymentIntentSucceededService = paymentIntentSucceededService;
        this.paymentIntentCanceledService = paymentIntentCanceledService;
        this.endpointSecret = stripeApiSecret.END_POINT_SECRET;
    }

    public void handle(PostPaymentEvent postPaymentEvent){
        logger.info("handle stripe event.");
        Event defaultEvent;
        try {
            defaultEvent = ApiResource.GSON.fromJson(postPaymentEvent.getEventJsonString(), Event.class);
        } catch (JsonSyntaxException e) {
            logger.error("⚠️  Webhook error while parsing basic request.");
            throw new RuntimeException(e);
        }
        String payload = postPaymentEvent.getEventJsonString();
        event = postPaymentEvent
                .getStripeSignatureHeader()
                .flatMap(signature->{
                    try {
                        Event endPointSecretEvent = Webhook.constructEvent(
                                payload, signature, endpointSecret
                        );
                        return Optional.of(endPointSecretEvent);
                    } catch (SignatureVerificationException e) {
                        logger.error("⚠️  Webhook error while validating signature.");
                        throw new RuntimeException(e);
                    }
                })
                .orElse(defaultEvent);
        deserializedEventData();
        handleStripeEvent();
    }

    private void deserializedEventData(){
        logger.info("Deserialized event data object");
        EventDataObjectDeserializer dataObjectDeserializer = event.getDataObjectDeserializer();
        if (dataObjectDeserializer.getObject().isPresent()) {
            stripeObject = dataObjectDeserializer.getObject().get();
        } else {
            throw new RuntimeException("Deserialize strip event data object failed.");
        }
    }

    private void handleStripeEvent(){
        // Handle the event
        if(event.getType().equals("payment_intent.succeeded")){
            logger.info("handle payment intent succeeded event: " + event.getType());
            PaymentIntent paymentIntent = (PaymentIntent)stripeObject;
            paymentIntentSucceededService.handleEvent(paymentIntent.getId());
            return;
        }
        if(event.getType().equals("payment_intent.canceled")){
            logger.info("handle payment intent canceled event: " + event.getType());
            PaymentIntent paymentIntent = (PaymentIntent)stripeObject;
            paymentIntentCanceledService.handleEvent(stripeObject);
            return;
        }
        if(event.getType().equals("payment_method.attached")){
            logger.info("handle payment method attached event: " + event.getType());
            PaymentMethod paymentMethod = (PaymentMethod) stripeObject;
            return;
        }
        logger.warn("Unhandled event type: " + event.getType());
    }
}
