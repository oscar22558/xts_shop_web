package com.xtsshop.app.features.orders;

import com.xtsshop.app.advices.exception.RecordNotFoundException;
import com.xtsshop.app.db.entities.Order;
import com.xtsshop.app.db.repositories.ItemJpaRepository;
import com.xtsshop.app.db.seed.DevelopmentDataSeed;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
@Transactional
public class CancelOrderTestHelper {

    private OrderTestHelper orderTestHelper;
    private DevelopmentDataSeed dataSeed;
    private ItemJpaRepository itemJpaRepository;
    private String route = "/api/orders/{orderId}/cancel";

    public CancelOrderTestHelper(OrderTestHelper orderTestHelper, DevelopmentDataSeed dataSeed, ItemJpaRepository itemJpaRepository) {
        this.orderTestHelper = orderTestHelper;
        this.dataSeed = dataSeed;
        this.itemJpaRepository = itemJpaRepository;
    }

    public void insertData(){
        dataSeed.insertData();
    }

    public void insertOrderForAllNormalUsers(){
        orderTestHelper.insertOrderForNewUser(orderTestHelper.insertNewUser());
        orderTestHelper.insertOrderForUser(orderTestHelper.getUser());
    }


    public long getIdOfOrderToCancel(){
        return orderTestHelper
                .getLatestOrder()
                .orElseThrow(()->new RecordNotFoundException("No order"))
                .getId();
    }

    public Order getLatestOrder(){
        return orderTestHelper.getLatestOrder().orElseThrow(()->new RecordNotFoundException("No order"));
    }
    public String getCancelOrderRoute(){
        return route;
    }

    public int getAppleStock(){
        return itemJpaRepository.findAllByName("apple").get(0).getStock();
    }

    public int getOrangeStock(){
        return itemJpaRepository.findAllByName("orange").get(0).getStock();
    }

}