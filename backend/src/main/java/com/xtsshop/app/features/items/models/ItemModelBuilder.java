package com.xtsshop.app.features.items.models;

import com.xtsshop.app.db.entities.Brand;
import com.xtsshop.app.db.entities.Item;
import com.xtsshop.app.features.storage.FilePathToUrlConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class ItemModelBuilder {

    private Item itemEntity;
    private FilePathToUrlConverter filePathToUrlConverter;
    private Optional<PriceHistoryPresentationModel> priceHistoryModel;
    private String brand;
    public ItemModelBuilder(){
       priceHistoryModel = Optional.empty();
    }
    public ItemModelBuilder setItemEntity(Item itemEntity) {
        this.itemEntity = itemEntity;
        return this;
    }

    public ItemModelBuilder setFilePathToUrlConverter(FilePathToUrlConverter converter) {
        filePathToUrlConverter = converter;
        return this;
    }

    public ItemModelBuilder replacePriceHistoryModel(PriceHistoryPresentationModel priceHistoryPresentationModel) {
        this.priceHistoryModel = Optional.of(priceHistoryPresentationModel);
        return this;
    }

    public ItemModelBuilder setBrand(Brand brand) {
        this.brand = brand.getName();
        return this;
    }

    public ItemRepresentationModel build(){
        if(itemEntity == null ) throw new NullPointerException("itemEntity cannot be null");
        if(filePathToUrlConverter == null) throw new NullPointerException("storageService cannot be null");
        Logger logger = LoggerFactory.getLogger(ItemModelBuilder.class);
        if(itemEntity.getImage() == null){
            logger.info("=== item without image=====");
            logger.info(itemEntity.getName());
        }
        String imgUrl = filePathToUrlConverter.getUrl(itemEntity.getImage().getPath());
        // price = null if no record
        PriceHistoryPresentationModel price = priceHistoryModel.orElse(
                itemEntity.getLatestPriceHistory()
                .flatMap(priceHistory -> Optional.of(PriceHistoryPresentationModel.from(priceHistory)))
                .orElse(null)
        );
        ItemRepresentationModel model = new ItemRepresentationModel();
        model.setId(itemEntity.getId());
        model.setCreatedAt(itemEntity.getCreatedAt());
        model.setUpdatedAt(itemEntity.getUpdatedAt());
        model.setName(itemEntity.getName());
        model.setPrice(price);
        model.setImgUrl(imgUrl);
        model.setManufacturer(itemEntity.getManufacturer());
        model.setStock(itemEntity.getStock());
        model.setBrand(brand);
        return model;
    }

}