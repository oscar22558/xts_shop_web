package com.xtsshop.app.viewmodel.builder;

import com.xtsshop.app.assembler.ItemModelAssembler;
import com.xtsshop.app.db.entities.Order;
import com.xtsshop.app.db.entities.OrderedItem;
import com.xtsshop.app.db.entities.PriceHistory;
import com.xtsshop.app.service.storage.StorageService;
import com.xtsshop.app.util.DateTimeUtil;
import com.xtsshop.app.viewmodel.*;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class OrderModelBuilder {
    private Order entity;
    private StorageService storageService;

    public OrderModelBuilder setEntity(Order entity){
        this.entity = entity;
        return this;
    }
    public OrderModelBuilder setStorageService(StorageService storageService) {
        this.storageService = storageService;
        return this;
    }

    public OrderModel build(){
        OrderModel model = new OrderModel();
        model.setAddress(AddressViewModel.from(entity.getShippingAddress()));
        model.setUser(UserViewModel.from(entity.getUser()));
        model.setOrderStatus(entity.getStatus().name());
        Collection<OrderedItemModel> orderedItemModels = entity.getOrderedItems().stream().map(orderedItem->{
            ItemModelBuilder itemModelBuilder = new ItemModelBuilder()
                    .setItemEntity(orderedItem.getItem())
                    .setStorageService(storageService);
            Optional.ofNullable(orderedItem.getOrderPrice())
                    .ifPresent((history)->{
                        itemModelBuilder.setPriceHistoryModel(
                            new PriceHistoryModel(history.getId(), history.getCreatedAt(), history.getValue())
                        );
                    });
            return new OrderedItemModel(
                    itemModelBuilder.build(),
                    orderedItem.getQuantity()
            );
        }).collect(Collectors.toList());
        model.setItems(orderedItemModels);
        return model;
    }
}
