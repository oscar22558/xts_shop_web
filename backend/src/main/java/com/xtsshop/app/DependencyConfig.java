package com.xtsshop.app;

import com.xtsshop.app.features.users.payment.*;
import com.xtsshop.app.features.users.payment.models.StripeApiSecret;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile({"test", "production"})
public class DependencyConfig {
    @Bean
    public CreatePaymentIntentService createPaymentIntentService(CreateOrderService createOrderService, OrderTotalCalculator orderAmountCalculator, StripeApiSecret stripeApiSecret){
        return new CreatePaymentIntentServiceImp(
               createOrderService, orderAmountCalculator, stripeApiSecret
        );
    }

    @Bean
    public UpdatePaymentIntentService updatePaymentIntentService(OrderTotalCalculator orderAmountCalculator, UpdateOrderServiceImp updateOrderService, StripeApiSecret stripeApiSecret){
        return new UpdatePaymentIntentServiceImp(
                orderAmountCalculator, updateOrderService, stripeApiSecret
        );
    }

    @Bean
    public CancelPaymentIntentService cancelPaymentIntentService(UserPaymentRepo userPaymentRepo){
        return new CancelPaymentIntentServiceImp(userPaymentRepo);
    }
}
