package com.xtsshop.app.viewmodel.builder;

import com.xtsshop.app.db.entities.Order;
import com.xtsshop.app.db.entities.OrderedItem;
import com.xtsshop.app.domain.service.storage.FilePathToUrlConverter;
import com.xtsshop.app.domain.service.storage.StorageService;
import com.xtsshop.app.viewmodel.*;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

public class OrderModelBuilder {
    private Order entity;
    private FilePathToUrlConverter filePathToUrlConverter;

    public OrderModelBuilder setEntity(Order entity){
        this.entity = entity;
        return this;
    }
    public OrderModelBuilder setFilePathToUrlConverter(FilePathToUrlConverter converter) {
        this.filePathToUrlConverter = converter;
        return this;
    }

    public OrderModel build(){
        OrderModel model = new OrderModel();
        model.setAddress(AddressViewModel.from(entity.getShippingAddress()));
        model.setUser(UserViewModel.from(entity.getUser()));
        model.setOrderStatus(entity.getStatus().name());
        Collection<OrderedItemModel> orderedItemModels = getOrderItemViewModels(entity);
        model.setItems(orderedItemModels);
        return model;
    }

    private Collection<OrderedItemModel> getOrderItemViewModels(Order entity){
        return entity.getOrderedItems().stream().map(orderedItem->new OrderedItemModel(
            buildItemModel(orderedItem),
            orderedItem.getQuantity()
        )).collect(Collectors.toList());
    }
    private ItemModel buildItemModel(OrderedItem orderedItem){
        ItemModelBuilder builder = new ItemModelBuilder()
                .setItemEntity(orderedItem.getItem())
                .setFilePathToUrlConverter(filePathToUrlConverter);
        replaceOrderPrice(builder, orderedItem);
        builder.setBrand(orderedItem.getItem().getBrand());
        return builder.build();
    }
    private ItemModelBuilder replaceOrderPrice(ItemModelBuilder builder, OrderedItem orderedItem){
        Optional.ofNullable(orderedItem.getOrderPrice())
                .ifPresent((history)->{
                    builder.replacePriceHistoryModel(
                            new PriceHistoryViewModel(history.getId(), history.getCreatedAt(), history.getValue())
                    );
                });
        return builder;
    }
}
