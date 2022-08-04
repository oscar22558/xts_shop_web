package com.xtsshop.app;

import com.xtsshop.app.controller.users.payment.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile({"test", "production"})
public class DependencyConfig {
    @Bean
    public CreatePaymentIntentService createPaymentIntentService(CreateOrderService createOrderService, OrderAmountCalculator orderAmountCalculator){
        return new CreatePaymentIntentServiceImp(
               createOrderService, orderAmountCalculator
        );
    }

    @Bean
    public UpdatePaymentIntentService updatePaymentIntentService(OrderAmountCalculator orderAmountCalculator, UpdateOrderServiceImp updateOrderService){
        return new UpdatePaymentIntentServiceImp(
                orderAmountCalculator, updateOrderService
        );
    }

    @Bean
    public CancelPaymentIntentService cancelPaymentIntentService(UserPaymentRepo userPaymentRepo){
        return new CancelPaymentIntentServiceImp(userPaymentRepo);
    }
}
