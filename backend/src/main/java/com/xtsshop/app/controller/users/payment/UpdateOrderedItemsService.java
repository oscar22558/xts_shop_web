package com.xtsshop.app.controller.users.payment;

import com.xtsshop.app.controller.users.payment.models.ItemQuantity;
import com.xtsshop.app.controller.users.payment.models.UpdateOrderedItemsRequest;
import com.xtsshop.app.db.entities.Item;
import com.xtsshop.app.db.entities.Order;
import com.xtsshop.app.db.entities.OrderedItem;
import com.xtsshop.app.db.repositories.OrderedItemJpaRepository;
import com.xtsshop.app.helpers.DateTimeHelper;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class UpdateOrderedItemsService {
    private UserPaymentRepo userPaymentRepo;
    private OrderedItemJpaRepository orderedItemJpaRepository;
    private List<ItemQuantity> itemQuantities;
    private List<OrderedItem> originalOrderedItems;
    private List<OrderedItem> updatedOrderedItemList;
    private Order order;

    public UpdateOrderedItemsService(UserPaymentRepo userPaymentRepo, OrderedItemJpaRepository orderedItemJpaRepository) {
        this.userPaymentRepo = userPaymentRepo;
        this.orderedItemJpaRepository = orderedItemJpaRepository;
    }

    public void update(UpdateOrderedItemsRequest request){
        this.itemQuantities = request.getItemQuantities();
        this.originalOrderedItems = request.getOrder().getOrderedItems();
        this.order = request.getOrder();
        itemQuantities.forEach(this::updateItemQuantity);
        this.updatedOrderedItemList = new ArrayList<>();
        updatedOrderedItemList.addAll(originalOrderedItems);
        orderedItemJpaRepository.saveAll(updatedOrderedItemList);
    }

    private void updateItemQuantity(ItemQuantity itemQuantity){
        originalOrderedItems
            .stream()
            .filter(orderedItem ->
                    orderedItem.getItem().getId() == itemQuantity.getItemId()
            ).findFirst()
            .ifPresentOrElse(
                element-> updateItemStock(element, itemQuantity.getQuantity()),
                () -> createOrderItem(itemQuantity)
            );
    }

    private void updateItemStock(OrderedItem orderItem, int newQuantity){
        orderItem.updateQuantity(newQuantity);
    }

    private void createOrderItem(ItemQuantity itemQuantity){
        Date now = new DateTimeHelper().now();
        long itemId = itemQuantity.getItemId();
        int orderedQuantity = itemQuantity.getQuantity();
        Item itemEntity = userPaymentRepo.findItemById(itemId);
        OrderedItem orderedItem = new OrderedItem();
        orderedItem.setCreatedAt(now);
        orderedItem.setUpdatedAt(now);
        orderedItem.setItem(itemEntity);
        orderedItem.setQuantity(orderedQuantity);
        orderedItem.setOrder(order);
        updatedOrderedItemList.add(orderedItem);
    }
}
