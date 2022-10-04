package com.xtsshop.app.features.users.payment;

import org.springframework.boot.test.context.TestComponent;

import javax.transaction.Transactional;

import com.xtsshop.app.features.users.payment.data.FakeOrderData;

import com.xtsshop.app.db.repositories.ItemJpaRepository;
import com.xtsshop.app.db.repositories.OrderJpaRepository;
import com.xtsshop.app.db.repositories.UserJpaRepository;
import com.xtsshop.app.db.seed.TestDataSeed;

@TestComponent
@Transactional
public class UpdatePaymentIntentTestHelper extends CreatePaymentIntentTestHelper {
    private FakeOrderData fakeOrderData;

    public UpdatePaymentIntentTestHelper(UserJpaRepository userJpaRepository, ItemJpaRepository itemJpaRepository, OrderJpaRepository orderJpaRepository, TestDataSeed data, FakeOrderData fakeOrderData) {
        super(userJpaRepository, itemJpaRepository, orderJpaRepository, data);
        this.fakeOrderData = fakeOrderData;
    }

    public void insertData(){
        super.insertData();
        fakeOrderData.setUsername("marry123");
        fakeOrderData.insert();
    }
}
