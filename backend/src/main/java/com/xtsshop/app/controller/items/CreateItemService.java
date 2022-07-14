package com.xtsshop.app.controller.items;

import com.xtsshop.app.advices.exception.RecordNotFoundException;
import com.xtsshop.app.controller.items.images.models.CreateItemImageInput;
import com.xtsshop.app.controller.items.models.CreateItemRequest;
import com.xtsshop.app.db.entities.*;
import com.xtsshop.app.db.entities.builder.ItemBuilder;
import com.xtsshop.app.db.repositories.BrandRepository;
import com.xtsshop.app.db.repositories.CategoryRepository;
import com.xtsshop.app.db.repositories.ItemRepository;
import com.xtsshop.app.controller.items.images.CreateItemImage;
import com.xtsshop.app.controller.items.price.CreatePriceHistories;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@Service
public class CreateItemService {

    private ItemRepository repository;
    private CreateItemImage createItemImage;
    private CategoryRepository categoryRepository;
    private BrandRepository brandRepository;
    private CreatePriceHistories createPriceHistories;
    private Category category;
    private Brand brand;
    private Item item;

    public CreateItemService(ItemRepository repository, CreateItemImage createItemImage, CategoryRepository categoryRepository, BrandRepository brandRepository, CreatePriceHistories createPriceHistories) {
        this.repository = repository;
        this.createItemImage = createItemImage;
        this.categoryRepository = categoryRepository;
        this.brandRepository = brandRepository;
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
        category = categoryRepository
                .findById(id)
                .orElseThrow(()->new RecordNotFoundException("Category with id "+id+" not found"));
    }

    public void setBrand(Long brandId){
        brand = brandRepository
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
        Image image = createItemImage
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
