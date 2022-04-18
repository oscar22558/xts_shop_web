package com.xtsshop.app.http.items;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xtsshop.app.db.entities.Image;
import com.xtsshop.app.db.entities.Item;
import com.xtsshop.app.db.entities.PriceHistory;
import com.xtsshop.app.db.entities.builder.PriceHistoryBuilder;
import com.xtsshop.app.db.repositories.ImageRepository;
import com.xtsshop.app.db.repositories.ItemRepository;
import com.xtsshop.app.service.storage.StorageService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Component("tests.http.items.Util")
public class Util {
    private String route = "/api/items";
    private ItemRepository repository;
    private ImageRepository imageRepository;
    private StorageService storageService;
    public Util(
        ItemRepository repository,
        ImageRepository imageRepository,
        @Qualifier("ImageStorageService") StorageService storageService
    ){
        this.repository = repository;
        this.imageRepository = imageRepository;
        this.storageService = storageService;
    }

    public Item latestItem(){
        return repository.findAll().stream()
                .sorted(Comparator.comparingInt(item -> (int) item.getCreatedAt().getTime()))
                .collect(Collectors.toList())
                .get(0);
    }

    public Image latestImage(){
        return imageRepository.findAll().stream()
                .sorted((t1, t2) -> (int)t2.getId() - (int)t1.getId())
                .collect(Collectors.toList())
                .get(0);
    }

    @Transactional
    public void updateAllItemsPrice(){
        List<Item> items = repository.findAll();
        updatePrice(items.get(0), 22.2f);
        updatePrice(items.get(1), 33.2f);
        updatePrice(items.get(2), 54.2f);
        updatePrice(items.get(3), 65.2f);
    }
    public void updatePrice(Item item, float price){
        PriceHistory priceHistory = new PriceHistoryBuilder()
                .setItem(item)
                .setValue(price)
                .build();
        item.setPrice(price);
        item.getPriceHistories().add(priceHistory);
        repository.save(item);
    }
    public String getRoute(){
        return route;
    }
    public String getRouteWithId(){
        return route+"/{id}";
    }
    public String getUpdateImageRoute(){
        return route+"/{id}/image";
    }
}
