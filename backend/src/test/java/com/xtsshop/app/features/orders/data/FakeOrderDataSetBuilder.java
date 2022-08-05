package com.xtsshop.app.features.orders.data;

import com.xtsshop.app.db.entities.OrderedItem;
import com.xtsshop.app.db.repositories.ItemJpaRepository;
import com.xtsshop.app.db.repositories.OrderJpaRepository;
import com.xtsshop.app.db.repositories.OrderedItemJpaRepository;
import com.xtsshop.app.db.repositories.UserJpaRepository;
import org.springframework.boot.test.context.TestComponent;

import java.util.List;

@TestComponent
public class FakeOrderDataSetBuilder {
    private String username;
    private int orderedItemDataSetIndex;
    private UserJpaRepository userJpaRepository;
    private OrderJpaRepository orderJpaRepository;
    private OrderedItemJpaRepository orderedItemJpaRepository;
    private FakeOrderedItemDataSet fakeOrderedItemDataSet;

    public FakeOrderDataSetBuilder(UserJpaRepository userJpaRepository, OrderJpaRepository orderJpaRepository, OrderedItemJpaRepository orderedItemJpaRepository, FakeOrderedItemDataSet fakeOrderedItemDataSet) {
        this.userJpaRepository = userJpaRepository;
        this.orderJpaRepository = orderJpaRepository;
        this.orderedItemJpaRepository = orderedItemJpaRepository;
        this.fakeOrderedItemDataSet = fakeOrderedItemDataSet;
    }

    public FakeOrderDataSetBuilder setUsername(String username) {
        this.username = username;
        return this;
    }

    public FakeOrderDataSetBuilder setOrderedItemDataSetIndex(int orderedItemDataSetIndex) {
        this.orderedItemDataSetIndex = orderedItemDataSetIndex;
        return this;
    }

    public FakeOrderDataSet build(){
        fakeOrderedItemDataSet.initOrderItemSets();
        return new FakeOrderDataSet(userJpaRepository, orderJpaRepository, orderedItemJpaRepository){
            @Override
            protected String getUsername() {
                return username;
            }

            @Override
            protected List<OrderedItem> getOrderItems() {
                return fakeOrderedItemDataSet.getOrderItemSet(orderedItemDataSetIndex);
            }
        };
    }
}
