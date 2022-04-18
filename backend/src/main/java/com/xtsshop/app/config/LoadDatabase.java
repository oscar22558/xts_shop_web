package com.xtsshop.app.config;


import com.xtsshop.app.db.seed.DevelopmentDataSeed;
import com.xtsshop.app.db.repositories.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(CategoryRepository repository, ItemRepository itemRepository, ImageRepository imageRepository, UserRepository userRepository, RoleRepository roleRepository, PrivilegeRepository privilegeRepository) {
        new DevelopmentDataSeed(
            repository, itemRepository, imageRepository, userRepository, roleRepository, privilegeRepository
        ).insertData();
        return args -> {};
    }
}
