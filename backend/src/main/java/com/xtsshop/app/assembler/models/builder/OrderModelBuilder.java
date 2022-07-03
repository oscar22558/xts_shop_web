package com.xtsshop.app.assembler.models.builder;

import com.xtsshop.app.assembler.models.*;
import com.xtsshop.app.db.entities.Order;
import com.xtsshop.app.db.entities.OrderedItem;
import com.xtsshop.app.domain.service.storage.FilePathToUrlConverter;
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
        OrderRepresentationModel model = new OrderRepresentationModel();
        model.setAddress(AddressRepresentationModel.from(entity.getShippingAddress()));
        model.setUser(UserRepresentationModel.from(entity.getUser()));
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
