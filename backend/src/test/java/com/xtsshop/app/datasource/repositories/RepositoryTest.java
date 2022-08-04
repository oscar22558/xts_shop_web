package com.xtsshop.app.datasource.repositories;


import com.xtsshop.app.advices.exception.RecordNotFoundException;
import com.xtsshop.app.db.entities.Category;
import com.xtsshop.app.db.entities.Image;
import com.xtsshop.app.db.entities.Item;
import com.xtsshop.app.db.repositories.CategoryJpaRepository;
import com.xtsshop.app.db.repositories.ImageJpaRepository;
import com.xtsshop.app.db.repositories.ItemJpaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import java.sql.Date;
import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
@AutoConfigureMockMvc
public class RepositoryTest {
    @Autowired
    ImageJpaRepository imageJpaRepository;
    @Autowired
    ItemJpaRepository itemJpaRepository;
    @Autowired
    CategoryJpaRepository categoryJpaRepository;

    @BeforeEach
    void beforeEach(){
        Date now = now();
        Category category = getCategory();
        Item item = itemJpaRepository.save(new Item(now, now, "item 1", 12.2f, "manufactorer 1", category, 100));
        Item itemWithOnlyId = new Item(item.getId());
        Image image = new Image(now, now, "/storage/image1.png", "image1.png", "png", itemWithOnlyId);
        imageJpaRepository.save(image);
    }
    @Test
    void testInsertInBeforeEach(){
        long count = imageJpaRepository.count();
        Image image = imageJpaRepository.findAll().get((int)count - 1);
        assertNotNull(image.getItem());
        assertNotNull(image.getItem().getName());
    }
    @Test
    void testInsertInTest(){

        Date now = now();
        Category category = getCategory();
        Item item = itemJpaRepository.save(new Item(now, now, "item 1", 12.2f, "manufactorer 1", category, 100));
        Item itemWithOnlyId = new Item(item.getId());
        Image image = new Image(now, now, "/storage/image1.png", "image1.png", "png", itemWithOnlyId);
        image = imageJpaRepository.save(image);
        image = imageJpaRepository.findById(image.getId()).orElseThrow();
        assertNotNull(image.getItem());
        assertNotNull(image.getItem().getImage());
    }

    @Test
    void testUpdateImage(){
        Date now = now();
        Item item = getLastItem();
        long oldImageId = item.getImage().getId();
        Image image = imageJpaRepository.save(new Image(now, now, "/storage/image1.png", "image1.png", "png", item));
        item.setImage(image);
        item = itemJpaRepository.save(item);
        image = imageJpaRepository.findById(image.getId()).orElseThrow();
        assertNotNull(item.getImage());
        assertNotNull(item.getImage().getItem());
        assertNotNull(image.getItem());
        assertNotNull(image.getItem().getImage());
        assertNull(imageJpaRepository.findById(oldImageId).orElse(null));
    }
    @Test
    void testSaveItemAndImageEntityDirectly() throws Exception{
        Logger logger = LoggerFactory.getLogger(RepositoryTest.class);
        Date now = now();
        Category category = getCategory();
        Item item = new Item(now, now, "item 1", 12.2f, "manufactorer 1", category, 100);
        Image image = new Image(now, now, "/storage/image1.png", "image1.png", "png", item);
        item.setImage(image);
        Long insertedItemId = itemJpaRepository.save(item).getId();
        Item insertedItem = itemJpaRepository.findById(insertedItemId).orElseThrow(()->new RecordNotFoundException("Item with id "+insertedItemId+" not found."));
        assertNotNull(insertedItem.getImage().getId());
        logger.info(String.valueOf(insertedItem.getImage().getId()));
        logger.info(String.valueOf(insertedItem.getId()));
    }

    @Test
    void testSaveImageOnly() throws Exception{
        Date now = now();
        Category category = getCategory();
        Item item = new Item(now, now, "item 1", 12.2f, "manufactorer 1", category, 100);
        item = itemJpaRepository.save(item);
        long itemId = item.getId();
        Image image = new Image(now, now, "/storage/image1.png", "image1.png", "png", item);
        item.setImage(image);
        imageJpaRepository.save(image);
        item = itemJpaRepository.findById(itemId).orElseThrow(()-> new RecordNotFoundException("Item with id "+ itemId+ " not found."));
        assertNotNull(item.getImage());
    }
    private Date now(){
       long nowInMillis = Calendar.getInstance().getTimeInMillis();
       return new Date(nowInMillis);
    }
    private Category getCategory(){
        long count = categoryJpaRepository.count();
        return categoryJpaRepository.findAll().get((int)count - 1);
    }
    private Item getLastItem(){
        long count = itemJpaRepository.count();
        return itemJpaRepository.findAll().get((int)count - 1);
    }
}
