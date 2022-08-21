package com.xtsshop.app.features.orders.models;

import com.xtsshop.app.AbstractRepresentationModel;
import com.xtsshop.app.db.entities.OrderedItem;
import com.xtsshop.app.features.storage.FilePathToUrlConverter;
import com.xtsshop.app.features.users.addresses.models.AddressRepresentationModel;
import lombok.Getter;
import lombok.Setter;

import java.io.File;

public class OrderedItemRepresentationModel implements AbstractRepresentationModel {
    private FilePathToUrlConverter filePathToUrlConverter;
    private OrderedItem orderedItemEntity;

    public OrderedItemRepresentationModel(OrderedItem orderedItemEntity) {
        this.orderedItemEntity = orderedItemEntity;
    }

    public void setFilePathToUrlConverter(FilePathToUrlConverter filePathToUrlConverter){
            this.filePathToUrlConverter = filePathToUrlConverter;
    }

    public long getItemId(){
        return orderedItemEntity.getItem().getId();
    }

    public long getOrderedItemId(){
        return orderedItemEntity.getId();
    }

    public int getQuantity(){
        return orderedItemEntity.getQuantity();
    }

    public String getName(){
        return orderedItemEntity.getItem().getName();
    }

    public float getPrice(){
        return orderedItemEntity.getOrderPriceValue();
    }

    public String getImgUrl(){
        String filePath = orderedItemEntity.getItem().getImage().getPath();
        return filePathToUrlConverter.getUrl(filePath);
    }
}