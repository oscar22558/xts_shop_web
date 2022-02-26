package com.xtsshop.app.config;


import com.xtsshop.app.db.entities.Category;
import com.xtsshop.app.db.entities.Item;
import com.xtsshop.app.db.repositories.CategoryRepository;
import com.xtsshop.app.db.repositories.ItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Date;
import java.util.Calendar;

@Configuration
class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(CategoryRepository repository, ItemRepository itemRepository) {
        Date now = new Date(Calendar.getInstance().getTimeInMillis());
        Category newCat1 = repository.save(new Category(now, "food", null));
        Category newCat2 = repository.save(new Category(now, "tools", null));

        Category newCat3 = repository.save(new Category(now, "fruit", newCat1));
        Category newCat4 = repository.save(new Category(now, "meat", newCat1));
        Category newCat5 = repository.save(new Category(now, "mobile device peripheral", newCat2));
        Category newCat6 = repository.save(new Category(now, "drawing tools", newCat2));
        Item item1 = itemRepository.save(new Item(now, now, "apple", 12.2f, "manufacturer 1", "/files/apple.png",  newCat3));
        Item item2 = itemRepository.save(new Item(now, now, "orange", 23.2f, "manufacturer 2", "/files/orange.png", newCat3));
        Item item3 = itemRepository.save(new Item(now, now, "USB data cable", 44.2f, "manufacturer 1", "/files/usb_data_cable.png", newCat5));
        Item item4 = itemRepository.save(new Item(now, now, "stand", 55.2f, "manufacturer 2", "/files/stand.png", newCat5));


        return args -> {
            log.info("Preloading " + newCat1);
            log.info("Preloading " + newCat2);
            log.info("Preloading " + newCat3);
            log.info("Preloading " + newCat4);
            log.info("Preloading " + newCat5);
            log.info("Preloading " + newCat6);
            log.info("Preloading " + item1);
            log.info("Preloading " + item2);
            log.info("Preloading " + item3);
            log.info("Preloading " + item4);
        };
    }
}
