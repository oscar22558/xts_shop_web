package com.xtsshop.app.viewmodel.builder;

import com.xtsshop.app.db.entities.Brand;
import com.xtsshop.app.db.entities.Item;
import com.xtsshop.app.domain.service.storage.StorageService;
import com.xtsshop.app.viewmodel.BrandViewModel;
import com.xtsshop.app.viewmodel.ItemModel;
import com.xtsshop.app.viewmodel.PriceHistoryViewModel;

import java.util.Optional;

public class ItemModelBuilder {

    private Item itemEntity;
    private StorageService storageService;
    private Optional<PriceHistoryViewModel> priceHistoryModel;
    private BrandViewModel brandViewModel;
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

    public ItemModelBuilder replacePriceHistoryModel(PriceHistoryViewModel priceHistoryViewModel) {
        this.priceHistoryModel = Optional.of(priceHistoryViewModel);
        return this;
    }

    public ItemModelBuilder setBrand(Brand brand) {
        brandViewModel = new BrandViewModelBuilder()
                .setId(brand.getId())
                .setName(brand.getName())
                .build();
        return this;
    }

    public ItemModel build(){
        if(itemEntity == null ) throw new NullPointerException("itemEntity cannot be null");
        if(storageService == null) throw new NullPointerException("storageService cannot be null");
        String imgUrl = storageService.url(itemEntity.getImage().getPath());
        // price = null if no record
        PriceHistoryViewModel price = priceHistoryModel.orElse(
                itemEntity.getLatestPriceHistory()
                .flatMap(priceHistory -> Optional.of(PriceHistoryViewModel.from(priceHistory)))
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
        model.setBrand(brandViewModel);
        return model;
    }

}
