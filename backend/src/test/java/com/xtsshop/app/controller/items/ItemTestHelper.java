package com.xtsshop.app.controller.items;

import com.xtsshop.app.db.entities.Image;
import com.xtsshop.app.db.entities.Item;
import com.xtsshop.app.db.entities.PriceHistory;
import com.xtsshop.app.db.entities.builder.PriceHistoryBuilder;
import com.xtsshop.app.db.repositories.BrandRepository;
import com.xtsshop.app.db.repositories.ImageRepository;
import com.xtsshop.app.db.repositories.ItemRepository;
import com.xtsshop.app.db.repositories.PriceHistoryRepository;
import com.xtsshop.app.db.seed.DevelopmentDataSeed;
import com.xtsshop.app.domain.service.storage.FilePathToUrlConverter;
import com.xtsshop.app.domain.service.storage.StorageService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Component("tests.http.items.Util")
@Transactional
public class ItemTestHelper {

    private String route = "/api/items";
    private ItemRepository repository;
    private ImageRepository imageRepository;
    private FilePathToUrlConverter filePathToUrlConverter;
    private BrandRepository brandRepository;
    private PriceHistoryRepository priceHistoryRepository;
    private DevelopmentDataSeed seed;

    public ItemTestHelper(
        ItemRepository repository,
        ImageRepository imageRepository,
        FilePathToUrlConverter filePathToUrlConverter,
        BrandRepository brandRepository,
        PriceHistoryRepository priceHistoryRepository,
        DevelopmentDataSeed seed
    ){
        this.repository = repository;
        this.imageRepository = imageRepository;
        this.filePathToUrlConverter = filePathToUrlConverter;
        this.brandRepository = brandRepository;
        this.priceHistoryRepository = priceHistoryRepository;
        this.seed = seed;
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
        priceHistoryRepository.save(priceHistory);
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

    public void insertData(){
        seed.insertData();
    }

    public MockMultipartFile getMockImageFile(){
        return  new MockMultipartFile(
                "image",
                "hello.png",
                MediaType.IMAGE_PNG_VALUE,
                "Hello, World!".getBytes()
        );
    }

    public String getFirstBrandId(){
        return String.valueOf(brandRepository.findAll().get(0).getId());
    }

    public String getFirstItemId(){
        Item entity = getRepository().findAll().get(0);
        return String.valueOf(entity.getId());
    }
}
