package com.xtsshop.app;

import com.xtsshop.app.features.users.payment.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import org.springframework.boot.test.context.TestConfiguration;

@TestConfiguration
@Profile("dev")
public class DependencyTestConfig {
    @Bean
    public CreatePaymentIntentService createPaymentIntentService(CreateOrderService createOrderService, OrderTotalCalculator orderTotalCalculator){
        return new CreatePaymentIntentServiceMockImp(
                createOrderService,
                orderTotalCalculator
        );
    }

    @Bean
    public UpdatePaymentIntentService updatePaymentIntentService(UpdateOrderServiceImp updateOrderService, OrderTotalCalculator orderTotalCalculator){
        return new UpdatePaymentIntentServiceMockImp(
                 updateOrderService,
                orderTotalCalculator
        );
    }

    @Bean
    public CancelPaymentIntentService cancelPaymentIntentService(UserPaymentRepo userPaymentRepo){
        return new CancelPaymentIntentServiceMockImp(userPaymentRepo);
    }
}
