package com.xtsshop.app.features.orders;

import com.xtsshop.app.db.seed.DevelopmentDataSeed;
import com.xtsshop.app.features.orders.data.FakeNewUserDataSet;
import com.xtsshop.app.features.orders.data.FakeOrderDataSetBuilder;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.transaction.annotation.Transactional;


@TestComponent
@Transactional
public class ListUserOrderTestHelper {
    private FakeOrderDataSetBuilder fakeOrderDataSetBuilder;
    private DevelopmentDataSeed developmentDataSeed;
    private FakeNewUserDataSet fakeNewUserDataSet;

    public ListUserOrderTestHelper(FakeOrderDataSetBuilder fakeOrderDataSetBuilder, DevelopmentDataSeed developmentDataSeed, FakeNewUserDataSet fakeNewUserDataSet) {
        this.fakeOrderDataSetBuilder = fakeOrderDataSetBuilder;
        this.developmentDataSeed = developmentDataSeed;
        this.fakeNewUserDataSet = fakeNewUserDataSet;
    }

    public void insertData(){
        developmentDataSeed.insertData();
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
