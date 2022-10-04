package com.xtsshop.app.features.orders;

import com.xtsshop.app.db.seed.TestDataSeed;
import com.xtsshop.app.features.orders.data.FakeNewUserDataSet;
import com.xtsshop.app.features.orders.data.FakeOrderDataSetBuilder;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.transaction.annotation.Transactional;


@TestComponent
@Transactional
public class ListUserOrderTestHelper {
    private FakeOrderDataSetBuilder fakeOrderDataSetBuilder;
    private TestDataSeed testDataSeed;
    private FakeNewUserDataSet fakeNewUserDataSet;

    public ListUserOrderTestHelper(FakeOrderDataSetBuilder fakeOrderDataSetBuilder, TestDataSeed testDataSeed, FakeNewUserDataSet fakeNewUserDataSet) {
        this.fakeOrderDataSetBuilder = fakeOrderDataSetBuilder;
        this.testDataSeed = testDataSeed;
        this.fakeNewUserDataSet = fakeNewUserDataSet;
    }

    public void insertData(){
        testDataSeed.insertData();
        fakeNewUserDataSet
                .setUsername("mario123")
                .insertData();
        fakeOrderDataSetBuilder
                .setUsername("marry123")
                .setOrderedItemDataSetIndex(0)
                .build()
                .insertOrderForUser();
        fakeOrderDataSetBuilder
                .setUsername("mario123")
                .setOrderedItemDataSetIndex(1)
                .build()
                .insertOrderForUser();
    }
}
