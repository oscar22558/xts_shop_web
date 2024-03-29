package com.xtsshop.app.features.orders;

import com.xtsshop.app.db.entities.Order;
import com.xtsshop.app.db.seed.DevDataSeed;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
@Transactional
public class GetTestHelper {
    private DevDataSeed dataSeed;
    private OrderTestHelper orderTestHelper;

    public GetTestHelper(DevDataSeed dataSeed, OrderTestHelper orderTestHelper) {
        this.dataSeed = dataSeed;
        this.orderTestHelper = orderTestHelper;
    }

    private String route = "/api/orders/{orderId}";

    public void insertData(){
        dataSeed.insertData();
    }

    public void updateData(){
        orderTestHelper.insertData();
    }

    public Order getOrderToQuery(){
        return orderTestHelper.getOrderOfUser();
    }

    public long getIdOfOrderToQuery(){
       return getOrderToQuery().getId();
    }

    public java.lang.String getRoute() {
        return route;
    }
}
