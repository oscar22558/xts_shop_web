package com.xtsshop.app.features.users.payment;

import com.xtsshop.app.advices.exception.RecordNotFoundException;
import com.xtsshop.app.features.users.payment.data.FakeOrderData;
import com.xtsshop.app.features.users.payment.models.FakePaymentDetail;
import com.xtsshop.app.db.entities.Order;
import com.xtsshop.app.db.repositories.OrderJpaRepository;
import com.xtsshop.app.db.seed.TestDataSeed;
import org.springframework.boot.test.context.TestComponent;

import javax.transaction.Transactional;

@TestComponent
@Transactional
public class CancelPaymentIntentTestHelper {
    private TestDataSeed dataSet;
    private OrderJpaRepository orderJpaRepository;
    private FakeOrderData fakeOrderData;

    public CancelPaymentIntentTestHelper(TestDataSeed dataSet, OrderJpaRepository orderJpaRepository, FakeOrderData fakeOrderData) {
        this.dataSet = dataSet;
        this.orderJpaRepository = orderJpaRepository;
        this.fakeOrderData = fakeOrderData;
    }

    public void insertData(){
        dataSet.insertData();
        fakeOrderData.setUsername("marry123");
        fakeOrderData.insert();
    }

    public String getPaymentIntentId(){
        return FakePaymentDetail.PAYMENT_INTENT_ID;
    }

    public Order getOrder(){
        return orderJpaRepository.findByPaymentIntentId(getPaymentIntentId())
                .orElseThrow(()->new RecordNotFoundException("Order not found."));
    }
}
