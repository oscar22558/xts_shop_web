package com.xtsshop.app.assembler.models.builder;

import com.xtsshop.app.assembler.models.BrandRepresentationModel;
import com.xtsshop.app.db.entities.Brand;
import com.xtsshop.app.db.entities.Item;
import com.xtsshop.app.domain.service.storage.FilePathToUrlConverter;
import com.xtsshop.app.assembler.models.ItemRepresentationModel;
import com.xtsshop.app.assembler.models.PriceHistoryPresentationModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class ItemModelBuilder {

    private Item itemEntity;
    private FilePathToUrlConverter filePathToUrlConverter;
    private Optional<PriceHistoryPresentationModel> priceHistoryModel;
    private BrandRepresentationModel brandRepresentationModel;
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
        brandRepresentationModel = new BrandRepresentationModel(brand);
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
        model.setBrand(brandRepresentationModel);
        return model;
    }

}
