package com.xtsshop.app.features.users.payment;

import com.xtsshop.app.advices.exception.RecordNotFoundException;
import com.xtsshop.app.db.entities.Address;
import com.xtsshop.app.db.entities.Invoice;
import com.xtsshop.app.db.entities.Order;
import com.xtsshop.app.db.entities.OrderedItem;
import com.xtsshop.app.db.repositories.ItemJpaRepository;
import com.xtsshop.app.db.repositories.OrderJpaRepository;
import com.xtsshop.app.db.repositories.UserJpaRepository;
import com.xtsshop.app.db.seed.DevDataSeed;
import org.springframework.boot.test.context.TestComponent;

import javax.transaction.Transactional;
import java.util.Optional;

@TestComponent
@Transactional
public class CreatePaymentIntentTestHelper {
    private UserJpaRepository userJpaRepository;
    private ItemJpaRepository itemJpaRepository;
    private OrderJpaRepository orderJpaRepository;
    private DevDataSeed data;

    public CreatePaymentIntentTestHelper(UserJpaRepository userJpaRepository, ItemJpaRepository itemJpaRepository, OrderJpaRepository orderJpaRepository, DevDataSeed data) {
        this.userJpaRepository = userJpaRepository;
        this.itemJpaRepository = itemJpaRepository;
        this.orderJpaRepository = orderJpaRepository;
        this.data = data;
    }

    public void insertData(){
        data.insertData();
    }

    public long getShippingAddressIdByUsername(String username){
        Address address = Optional.ofNullable(userJpaRepository.findUserByUsername(username))
                .orElseThrow(()->new RecordNotFoundException("User " + username + " not found."))
                .getAddresses()
                .get(0);
        return Optional.ofNullable(address)
                .orElseThrow(()->new RuntimeException("No address found."))
                .getId();
    }

    public long getItemIdToOrder(){
        return Optional.ofNullable(itemJpaRepository.findAll().get(0))
                .orElseThrow(()->new RuntimeException("No item found in database."))
                .getId();
    }

    public Order getLatestOrder(){
        int orderCount = orderJpaRepository.findAll().size();
        return orderJpaRepository.findAll().get(orderCount-1);
    }

    public Invoice getLatestOrderInvoice(){
        return getLatestOrder().getInvoice();
    }

    public OrderedItem getLatestOrderItem(int index){
        return getLatestOrder().getOrderedItems().get(index);
    }
    public int getItemStock(int index){
        return itemJpaRepository.findAll().get(index).getStock();
    }
}
