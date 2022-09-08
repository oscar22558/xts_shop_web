package com.xtsshop.app.features.users.payment;

import javax.validation.Valid;

import com.xtsshop.app.features.users.payment.models.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserPaymentController {
    private Logger logger = LoggerFactory.getLogger(UserPaymentController.class);
    private CreatePaymentIntentService createPaymentIntentService;
    private UpdatePaymentIntentService updatePaymentIntentService;
    private PostPaymentEventService postPaymentEventService;
    private CancelPaymentIntentService cancelPaymentIntentService;

    public UserPaymentController(CreatePaymentIntentService createPaymentIntentService, UpdatePaymentIntentService updatePaymentIntentService, PostPaymentEventService postPaymentEventService, CancelPaymentIntentService cancelPaymentIntentService) {
        this.createPaymentIntentService = createPaymentIntentService;
        this.updatePaymentIntentService = updatePaymentIntentService;
        this.postPaymentEventService = postPaymentEventService;
        this.cancelPaymentIntentService = cancelPaymentIntentService;
    }

    @PostMapping("/api/payment-intent")
    public ResponseEntity<?> createPaymentIntent(
            @RequestBody
            @Valid
            CreatePaymentIntentForm createPaymentIntentForm
    ) {
        CreatePaymentIntentResponse response = createPaymentIntentService.createIntent(createPaymentIntentForm);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/api/payment-intent")
    public ResponseEntity<?> updatePaymentIntent(
            @RequestBody
            @Valid
                    UpdatePaymentIntentForm updatePaymentIntentForm
    ) {
        updatePaymentIntentService.execute(updatePaymentIntentForm);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/api/payment-intent")
    public ResponseEntity<?> cancelPaymentIntent(@RequestBody @Valid CancelPaymentIntentRequest request){
        cancelPaymentIntentService.execute(request);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/webhook")
    public ResponseEntity<?> handlePostPaymentEvent(@RequestBody String requestBody, @RequestHeader("Stripe-Signature") String stripeSignatureHeader){
        logger.info("Received event from stripe.");
        postPaymentEventService.handle(new PostPaymentEvent(
                requestBody,
                stripeSignatureHeader
        ));
        return ResponseEntity.ok().build();
    }
}
