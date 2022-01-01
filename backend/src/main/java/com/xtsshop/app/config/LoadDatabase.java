package com.xtsshop.app.config;


import com.xtsshop.app.entities.Category;
import com.xtsshop.app.repository.CategoryRepository;
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
    CommandLineRunner initDatabase(CategoryRepository repository) {
        long now = Calendar.getInstance().getTimeInMillis();
        Category newCat1 = repository.save(new Category(new Date(now), "food", null));
        Category newCat2 = repository.save(new Category(new Date(now), "tools", null));

        Category newCat3 = repository.save(new Category(new Date(now), "fruit", newCat1));
        Category newCat4 = repository.save(new Category(new Date(now), "meat", newCat1));
        Category newCat5 = repository.save(new Category(new Date(now), "mobile device peripheral", newCat2));
        Category newCat6 = repository.save(new Category(new Date(now), "drawing tools", newCat2));
        return args -> {
            log.info("Preloading " + newCat1);
            log.info("Preloading " + newCat2);
            log.info("Preloading " + newCat3);
            log.info("Preloading " + newCat4);
            log.info("Preloading " + newCat5);
            log.info("Preloading " + newCat6);
        };
    }
}
