package com.xtsshop.app.features.users.payment;

import com.xtsshop.app.advices.exception.RecordNotFoundException;
import com.xtsshop.app.advices.exception.UnAuthorizationException;
import com.xtsshop.app.features.authentication.UserIdentityService;
import com.xtsshop.app.features.users.payment.models.CreateOrderRequest;
import com.xtsshop.app.features.users.payment.models.ItemQuantity;
import com.xtsshop.app.db.entities.*;
import com.xtsshop.app.features.orders.entitybuilders.OrderEntityBuilder;
import com.xtsshop.app.helpers.DateTimeHelper;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CreateOrderServiceImp implements CreateOrderService {
    private UserPaymentRepo userPaymentRepo;
    private UserIdentityService userIdentityService;
    private UpdateItemStockService updateItemStockService;

    public CreateOrderServiceImp(UserPaymentRepo userPaymentRepo, UserIdentityService userIdentityService, UpdateItemStockService updateItemStockService) {
        this.userPaymentRepo = userPaymentRepo;
        this.userIdentityService = userIdentityService;
        this.updateItemStockService = updateItemStockService;
    }

    public void create(CreateOrderRequest request) throws RecordNotFoundException, UnAuthorizationException {
        List<OrderedItem> orderedItems = buildOrderItemList(request.getItemQuantities());
        AppUser user = getPaymentIntentUser();
        ShippingAddress address = mapToShippingAddress(request);
        OrderStatus status = OrderStatus.WAITING_PAYMENT;
        Order orderEntity = new OrderEntityBuilder()
                .setShippingAddress(address)
                .setUser(user)
                .setStatus(status)
                .setOrderedItems(orderedItems)
                .build();

        orderEntity.setPaymentIntentId(request.getPaymentIntentId());

        request.getInvoice().setOrder(orderEntity);
        orderEntity.setInvoice(request.getInvoice());

        user.getOrders().add(orderEntity);
        orderedItems.forEach(orderedItem ->
            updateItemStockService.updateItemStock(orderedItem.getItem(), orderedItem.getQuantity())
        );
        orderEntity.setRecipientFirstName(request.getRecipientFirstName());
        orderEntity.setRecipientLastName(request.getRecipientLastName());
        orderEntity.setRecipientEmail(request.getRecipientEmail());
        orderEntity.setRecipientPhone(request.getRecipientPhone());
        userPaymentRepo.saveOrder(orderEntity);
    }

    private ShippingAddress mapToShippingAddress(CreateOrderRequest request){
        Date now = new DateTimeHelper().now();
        ShippingAddress address = new ShippingAddress();
        address.setCreatedAt(now);
        address.setUpdatedAt(now);
        address.setRow1(request.getRow1());
        address.setRow2(request.getRow2());
        address.setCountry(request.getCountry());
        address.setCity(request.getCity());
        address.setArea(request.getArea());
        address.setDistrict(request.getDistrict());
        return address;
    }
    private List<OrderedItem> buildOrderItemList(List<ItemQuantity> itemQuantities){
       return itemQuantities.stream()
               .map(this::buildOrderedItem)
               .collect(Collectors.toList());
    }

    private OrderedItem buildOrderedItem(ItemQuantity itemQuantity ){
        Date now = new DateTimeHelper().now();
        long itemId = itemQuantity.getItemId();
        int orderedQuantity = itemQuantity.getQuantity();
        Item itemEntity = userPaymentRepo.findItemById(itemId);
        OrderedItem orderedItem = new OrderedItem();
        orderedItem.setCreatedAt(now);
        orderedItem.setUpdatedAt(now);
        orderedItem.setItem(itemEntity);
        orderedItem.setPrice(itemEntity.getLatestPrice());
        orderedItem.setQuantity(orderedQuantity);
        return orderedItem;
    }

    private AppUser getPaymentIntentUser(){
        return userIdentityService.getUser();
    }
}
