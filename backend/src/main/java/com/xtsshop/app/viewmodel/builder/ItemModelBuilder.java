package com.xtsshop.app.viewmodel.builder;

import com.xtsshop.app.db.entities.Item;
import com.xtsshop.app.service.storage.StorageService;
import com.xtsshop.app.viewmodel.ItemModel;
import com.xtsshop.app.viewmodel.PriceHistoryModel;
import lombok.NoArgsConstructor;

import java.util.Optional;

public class ItemModelBuilder {

    private Item itemEntity;
    private StorageService storageService;
    private Optional<PriceHistoryModel> priceHistoryModel;
    public ItemModelBuilder(){
       priceHistoryModel = Optional.empty();
    }
    public ItemModelBuilder setItemEntity(Item itemEntity) {
        this.itemEntity = itemEntity;
        return this;
    }

    public ItemModelBuilder setStorageService(StorageService storageService) {
        this.storageService = storageService;
        return this;
    }

    public ItemModelBuilder setPriceHistoryModel(PriceHistoryModel priceHistoryModel) {
        this.priceHistoryModel = Optional.of(priceHistoryModel);
        return this;
    }

    public ItemModel build(){
        if(itemEntity == null ) throw new NullPointerException("itemEntity cannot be null");
        if(storageService == null) throw new NullPointerException("storageService cannot be null");
        String imgUrl = storageService.url(itemEntity.getImage().getPath());
        // price = null if no record
        PriceHistoryModel price = priceHistoryModel.orElse(
                itemEntity.getLatestPriceHistory()
                .flatMap(priceHistory -> Optional.of(PriceHistoryModel.from(priceHistory)))
                .orElse(null)
        );
        ItemModel model = new ItemModel();
        model.setId(itemEntity.getId());
        model.setCreatedAt(itemEntity.getCreatedAt());
        model.setUpdatedAt(itemEntity.getUpdatedAt());
        model.setName(itemEntity.getName());
        model.setPrice(price);
        model.setImgUrl(imgUrl);
        model.setManufacturer(itemEntity.getManufacturer());
        model.setStock(itemEntity.getStock());
        return model;
    }
}
