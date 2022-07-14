package com.xtsshop.app.controller.orders.models;

import com.xtsshop.app.controller.items.models.ItemModelBuilder;
import com.xtsshop.app.controller.items.models.ItemRepresentationModel;
import com.xtsshop.app.controller.items.models.PriceHistoryPresentationModel;
import com.xtsshop.app.controller.users.models.UserRepresentationModel;
import com.xtsshop.app.db.entities.Order;
import com.xtsshop.app.db.entities.OrderedItem;
import com.xtsshop.app.controller.storage.FilePathToUrlConverter;

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

    public OrderRepresentationModel build(){
        UserRepresentationModel userModel = new UserRepresentationModel(entity.getUser());
        AddressRepresentationModel addressModel = new AddressRepresentationModel(entity.getShippingAddress());
        OrderRepresentationModel model = new OrderRepresentationModel();
        model.setAddress(addressModel);
        model.setUser(userModel);
        model.setOrderStatus(entity.getStatus().name());
        Collection<OrderedItemRepresentationModel> orderedItemRepresentationModels = getOrderItemViewModels(entity);
        model.setItems(orderedItemRepresentationModels);
        return model;
    }

    private Collection<OrderedItemRepresentationModel> getOrderItemViewModels(Order entity){
        return entity.getOrderedItems().stream().map(orderedItem->new OrderedItemRepresentationModel(
            buildItemModel(orderedItem),
            orderedItem.getQuantity()
        )).collect(Collectors.toList());
    }
    private ItemRepresentationModel buildItemModel(OrderedItem orderedItem){
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
                            new PriceHistoryPresentationModel(history.getId(), history.getCreatedAt(), history.getValue())
                    );
                });
        return builder;
    }
}
