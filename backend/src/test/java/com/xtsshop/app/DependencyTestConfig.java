package com.xtsshop.app;

import com.xtsshop.app.controller.users.payment.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import org.springframework.boot.test.context.TestConfiguration;

@TestConfiguration
@Profile("dev")
public class DependencyTestConfig {
    @Bean
    public CreatePaymentIntentService createPaymentIntentService(CreateOrderService createOrderService){
        return new CreatePaymentIntentServiceMockImp(
                createOrderService
        );
    }

    @Bean
    public UpdatePaymentIntentService updatePaymentIntentService(UpdateOrderServiceImp updateOrderService){
        return new UpdatePaymentIntentServiceMockImp(
                 updateOrderService
        );
    }

    @Bean
    public CancelPaymentIntentService cancelPaymentIntentService(UserPaymentRepo userPaymentRepo){
        return new CancelPaymentIntentServiceMockImp(userPaymentRepo);
    }
}
