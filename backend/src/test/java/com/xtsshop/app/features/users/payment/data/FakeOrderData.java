package com.xtsshop.app.features.users.payment.data;

import com.xtsshop.app.advices.exception.RecordNotFoundException;
import com.xtsshop.app.features.users.payment.models.FakePaymentDetail;
import com.xtsshop.app.db.entities.*;
import com.xtsshop.app.db.repositories.ItemJpaRepository;
import com.xtsshop.app.db.repositories.OrderJpaRepository;
import com.xtsshop.app.db.repositories.UserJpaRepository;
import com.xtsshop.app.helpers.DateTimeHelper;
import org.springframework.boot.test.context.TestComponent;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@TestComponent
public class FakeOrderData {
    private Date now;
    private String username;
    private UserJpaRepository userJpaRepository;
    private ItemJpaRepository itemJpaRepository;
    private OrderJpaRepository orderJpaRepository;

    public FakeOrderData(UserJpaRepository userJpaRepository, ItemJpaRepository itemJpaRepository, OrderJpaRepository orderJpaRepository) {
        this.userJpaRepository = userJpaRepository;
        this.itemJpaRepository = itemJpaRepository;
        this.orderJpaRepository = orderJpaRepository;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void insert(){
        this.now = new DateTimeHelper().now();
        Order order = new Order();
        order.setUpdatedAt(now);
        order.setCreatedAt(now);
        order.setPaymentIntentId(getPaymentIntentId());
        order.setShippingAddress(getAddress());

        List<OrderedItem> orderedItemList = buildOrderItems(order);
        order.setOrderedItems(orderedItemList);

        order.setStatus(OrderStatus.WAITING_PAYMENT);
        order.setUser(getUser());
        orderJpaRepository.save(order);
    }

    public List<OrderedItem> buildOrderItems(Order order){
        List<OrderedItem> orderedItemList = new ArrayList<>();
        OrderedItem orderedItem = new OrderedItem();
        orderedItem.setCreatedAt(now);
        orderedItem.setUpdatedAt(now);
        orderedItem.setItem(getItemToOrder());
        orderedItem.setQuantity(8);
        orderedItem.setOrderPrice(getItemPriceForOrder());
        orderedItem.setOrder(order);
        orderedItemList.add(orderedItem);
        return orderedItemList;
    }

    public PriceHistory getItemPriceForOrder(){
        return getItemToOrder().getLatestPriceHistory()
                .orElseThrow(()-> new RecordNotFoundException("Price not defined."));
    }

    public Item getItemToOrder(){
        return itemJpaRepository.findAll().get(0);
    }

    public Address getAddress(){
        return getUser().getAddresses().get(0);
    }

    public AppUser getUser(){
        return userJpaRepository.findUserByUsername(username);
    }

    public String getPaymentIntentId(){
       return FakePaymentDetail.PAYMENT_INTENT_ID;
    }
}
