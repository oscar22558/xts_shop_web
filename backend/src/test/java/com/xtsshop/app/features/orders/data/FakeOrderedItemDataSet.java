package com.xtsshop.app.features.orders.data;

import com.xtsshop.app.db.entities.Item;
import com.xtsshop.app.db.entities.OrderedItem;
import com.xtsshop.app.db.repositories.ItemJpaRepository;
import com.xtsshop.app.helpers.DateTimeHelper;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@TestComponent
@Transactional
public class FakeOrderedItemDataSet {
    private List<List<OrderedItem>> orderedItemSets = new ArrayList<>();
    private ItemJpaRepository itemJpaRepository;

    public FakeOrderedItemDataSet(ItemJpaRepository itemJpaRepository) {
        this.itemJpaRepository = itemJpaRepository;
    }

    public void initOrderItemSets(){
        if(orderedItemSets.size() > 0){
            return;
        }
        Date now = new DateTimeHelper().now();
        List<Item> itemList = itemJpaRepository.findAll();
        List<OrderedItem> orderedItemSet1 = new ArrayList<>();
        orderedItemSet1.add(new OrderedItem(now, now, itemList.get(0), 2));
        orderedItemSet1.add(new OrderedItem(now, now, itemList.get(1), 7));
        orderedItemSet1.add(new OrderedItem(now, now, itemList.get(3), 5));

        orderedItemSets.add(orderedItemSet1);

        List<OrderedItem> orderedItemSet2 = new ArrayList<>();
        orderedItemSet2.add(new OrderedItem(now, now, itemList.get(1), 3));
        orderedItemSet2.add(new OrderedItem(now, now, itemList.get(3), 4));
        orderedItemSet2.add(new OrderedItem(now, now, itemList.get(2), 10));

        orderedItemSets.add(orderedItemSet2);
    }

    public List<OrderedItem> getOrderItemSet(int index){
        return orderedItemSets.get(index);
    }
}
