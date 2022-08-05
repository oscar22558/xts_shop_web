package com.xtsshop.app.features.items;

import com.xtsshop.app.db.entities.Image;
import com.xtsshop.app.db.entities.Item;
import com.xtsshop.app.db.entities.PriceHistory;
import com.xtsshop.app.db.entities.builder.PriceHistoryBuilder;
import com.xtsshop.app.db.repositories.BrandJpaRepository;
import com.xtsshop.app.db.repositories.ImageJpaRepository;
import com.xtsshop.app.db.repositories.ItemJpaRepository;
import com.xtsshop.app.db.repositories.PriceHistoryJpaRepository;
import com.xtsshop.app.db.seed.DevelopmentDataSeed;
import com.xtsshop.app.features.storage.FilePathToUrlConverter;
import lombok.Getter;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@TestComponent
@Transactional
public class ItemTestHelper {

    private String route = "/api/items";
    private ItemJpaRepository repository;
    private ImageJpaRepository imageJpaRepository;
    private FilePathToUrlConverter filePathToUrlConverter;
    private BrandJpaRepository brandJpaRepository;
    private PriceHistoryJpaRepository priceHistoryJpaRepository;
    private DevelopmentDataSeed seed;

    public ItemTestHelper(
        ItemJpaRepository repository,
        ImageJpaRepository imageJpaRepository,
        FilePathToUrlConverter filePathToUrlConverter,
        BrandJpaRepository brandJpaRepository,
        PriceHistoryJpaRepository priceHistoryJpaRepository,
        DevelopmentDataSeed seed
    ){
        this.repository = repository;
        this.imageJpaRepository = imageJpaRepository;
        this.filePathToUrlConverter = filePathToUrlConverter;
        this.brandJpaRepository = brandJpaRepository;
        this.priceHistoryJpaRepository = priceHistoryJpaRepository;
        this.seed = seed;
    }

    public Item latestItem(){
        return repository.findAll().stream()
                .sorted(Comparator.comparingInt(item -> (int) item.getCreatedAt().getTime()))
                .collect(Collectors.toList())
                .get(0);
    }

    public Image latestImage(){
        return imageJpaRepository.findAll().stream()
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
        priceHistoryJpaRepository.save(priceHistory);
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

    public String getBrandId(int index){
        return String.valueOf(brandJpaRepository.findAll().get(index).getId());
    }

    public String getItemIdStr(int index){
        Long itemId = getItemId(index);
        return String.valueOf(itemId);
    }

    public long getItemId(int index){
        return getRepository()
                .findAll()
                .get(index)
                .getId();
    }

    public String getItemName(int index){
        return getRepository()
                .findAll()
                .get(index)
                .getName();
    }

    public float getItemPrice(int index){
        return getRepository()
                .findAll()
                .get(index)
                .getPrice();
    }

    public int countItemPriceHistories(int index){
        return getRepository()
                .findAll()
                .get(index)
                .getPriceHistories()
                .size();
    }

    public String getItemManufacturer(int index){
        return getRepository()
                .findAll()
                .get(index)
                .getManufacturer();
    }

    public int getItemStock(int index){
        return getRepository()
                .findAll()
                .get(index)
                .getStock();
    }

    public String getItemImagePath(int index){
        return getRepository()
                .findAll()
                .get(index)
                .getImage()
                .getPath();
    }
}
