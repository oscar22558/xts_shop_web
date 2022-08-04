package com.xtsshop.app.controller.users.payment;

import com.xtsshop.app.controller.users.payment.data.FakeOrderData;
import com.xtsshop.app.db.entities.OrderStatus;
import com.xtsshop.app.db.repositories.OrderJpaRepository;
import com.xtsshop.app.db.seed.DevelopmentDataSeed;
import org.springframework.boot.test.context.TestComponent;

import javax.transaction.Transactional;

@TestComponent
@Transactional
public class PaymentIntentSucceededServiceTestHelper {
    private DevelopmentDataSeed data;
    private FakeOrderData fakeOrderData;
    private OrderJpaRepository orderJpaRepository;

    public PaymentIntentSucceededServiceTestHelper(DevelopmentDataSeed data, FakeOrderData fakeOrderData, OrderJpaRepository orderJpaRepository) {
        this.data = data;
        this.fakeOrderData = fakeOrderData;
        this.orderJpaRepository = orderJpaRepository;
    }

    public void insertData(){
        data.insertData();
        fakeOrderData.setUsername("marry123");
        fakeOrderData.insert();
    }

    public String getOrderPaymentIntentId(){
        return orderJpaRepository.findAll().get(0).getPaymentIntentId();
    }

    public OrderStatus getOrderStatus(){
        return orderJpaRepository.findAll().get(0).getStatus();
    }
}
