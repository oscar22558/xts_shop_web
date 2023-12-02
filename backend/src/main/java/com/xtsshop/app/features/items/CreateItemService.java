package com.xtsshop.app.features.items;

import com.xtsshop.app.advices.exception.RecordNotFoundException;
import com.xtsshop.app.features.items.images.models.CreateItemImageInput;
import com.xtsshop.app.features.items.models.CreateItemRequest;
import com.xtsshop.app.db.entities.*;
import com.xtsshop.app.db.entities.builder.ItemBuilder;
import com.xtsshop.app.db.repositories.BrandJpaRepository;
import com.xtsshop.app.db.repositories.CategoryJpaRepository;
import com.xtsshop.app.db.repositories.ItemJpaRepository;
import com.xtsshop.app.features.items.images.CreateImageService;
import com.xtsshop.app.features.items.price.CreatePriceHistories;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@Service
public class CreateItemService {

    private ItemJpaRepository repository;
    private CreateImageService createImageService;
    private CategoryJpaRepository categoryJpaRepository;
    private BrandJpaRepository brandJpaRepository;
    private CreatePriceHistories createPriceHistories;
    private Category category;
    private Brand brand;
    private Item item;

    public CreateItemService(ItemJpaRepository repository, CreateImageService createImageService, CategoryJpaRepository categoryJpaRepository, BrandJpaRepository brandJpaRepository, CreatePriceHistories createPriceHistories) {
        this.repository = repository;
        this.createImageService = createImageService;
        this.categoryJpaRepository = categoryJpaRepository;
        this.brandJpaRepository = brandJpaRepository;
        this.createPriceHistories = createPriceHistories;
    }

    public Item create(CreateItemRequest request) {
        setCategory(request.getCategoryId());
        setBrand(request.getBrandId());
        item = repository.save(toEntity(request));
        setImage(request.getImage());
        setPrice(request.getPrice());
        return repository.save(item);
    }

    public void setCategory(Long id){
        category = categoryJpaRepository
                .findById(id)
                .orElseThrow(()->new RecordNotFoundException("Category with id "+id+" not found"));
    }

    public void setBrand(Long brandId){
        brand = brandJpaRepository
                .findById(brandId)
                .orElseThrow(()->new RecordNotFoundException("Brand with id "+brandId+" not found"));
    }

    public Item toEntity(CreateItemRequest request) {
        return new ItemBuilder()
                .setName(request.getName())
                .setManufacturer(request.getManufacturer())
                .setCategory(category)
                .setBrand(brand)
                .setStock(request.getStock())
                .setPrice(request.getPrice())
                .build();
    }

    public void setImage(MultipartFile file){
        Image image = createImageService
            .create(new CreateItemImageInput(
                file,
                item
            ));
        item.setImage(image);
    }

    public void setPrice(float price){
        createPriceHistories.setItem(item);
        createPriceHistories.addPrice(price);
        List<PriceHistory> prices = createPriceHistories.save();
        item.getPriceHistories().addAll(prices);
    }


}
