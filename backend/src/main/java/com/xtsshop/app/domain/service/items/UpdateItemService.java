package com.xtsshop.app.domain.service.items;

import com.xtsshop.app.advice.exception.RecordNotFoundException;
import com.xtsshop.app.db.entities.Category;
import com.xtsshop.app.db.entities.Item;
import com.xtsshop.app.db.entities.PriceHistory;
import com.xtsshop.app.db.entities.builder.PriceHistoryBuilder;
import com.xtsshop.app.db.repositories.CategoryRepository;
import com.xtsshop.app.db.repositories.ItemRepository;
import com.xtsshop.app.domain.request.items.UpdateItemRequest;
import com.xtsshop.app.domain.service.items.images.ImagesService;
import com.xtsshop.app.util.DateTimeUtil;
import org.springframework.stereotype.Service;
import java.sql.Date;
import java.util.Optional;

@Service
public class UpdateItemService {

    private ItemRepository repository;
    private CategoryRepository categoryRepository;
    private ImagesService imagesService;

    private UpdateItemRequest request;
    private Item item;

    public Item update(UpdateItemRequest request) {
        this.request = request;
        findItemById(request.getId());
        updateEntity();
        return repository.save(item);
    }

    public void findItemById(Long id){
        item = repository
                .findById(id)
                .orElseThrow(()->new RecordNotFoundException("Item with id "+request.getId()+ " not found"));
    }

    public void updateEntity() {
        updateItemCategory();
        request.getName().ifPresent((name)->item.setName(name));
        request.getManufacturer().ifPresent((manufacturer)->item.setManufacturer(manufacturer));
        request.getStock().ifPresent((stock)->item.setStock(stock));
        updatePrice();
        updateItemUpdatedAt();
    }

    private void updatePrice(){
        boolean isPriceChanged = isPriceChanged();
        request.getPrice().ifPresent(value->{
            if(isPriceChanged){
                item.setPrice(value);
                PriceHistory priceHistory = new PriceHistoryBuilder()
                        .setItem(item)
                        .setValue(value)
                        .build();
                item.getPriceHistories().add(priceHistory);
            }
        });
    }
    private boolean isPriceChanged(){
        float latestPrice = item.getLatestPrice();
        return request
                .getPrice()
                .flatMap(value->Optional.of(latestPrice != value)).orElse(false);
    }

    public void updateItemCategory(){
        request.getCategoryId()
                .ifPresent((categoryId)->{
                    Category category = categoryRepository
                            .findById(categoryId)
                            .orElseThrow(()->new RecordNotFoundException("Category with id "+categoryId+" not found"));

                    item.setCategory(category);
                });
    }

    private void updateItemUpdatedAt(){
        Date now = new DateTimeUtil().now();
        UpdateItemRequest request = this.request;
        if(request.getName().isPresent() ||
                isPriceChanged() ||
                request.getManufacturer().isPresent() ||
                request.getCategoryId().isPresent() ||
                request.getStock().isPresent()
        ){
            item.setUpdatedAt(now);
        }
    }
}
