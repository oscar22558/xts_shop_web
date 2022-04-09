package com.xtsshop.app.config;


import com.xtsshop.app.db.entities.*;
import com.xtsshop.app.db.repositories.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.sql.Date;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

@Configuration
class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(CategoryRepository repository, ItemRepository itemRepository, ImageRepository imageRepository, UserRepository userRepository, RoleRepository roleRepository, PrivilegeRepository privilegeRepository) {
        Date now = new Date(Calendar.getInstance().getTimeInMillis());
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Category newCat1 = repository.save(new Category(now, now, "food", null));
        Category newCat2 = repository.save(new Category(now, now, "tools", null));
        Category newCat3 = repository.save(new Category(now, now,"fruit", newCat1));
        Category newCat4 = repository.save(new Category(now, now,"meat", newCat1));
        Category newCat5 = repository.save(new Category(now, now,"mobile device peripheral", newCat2));
        Category newCat6 = repository.save(new Category(now, now,"drawing tools", newCat2));
        Item item1 = itemRepository.save(new Item(now, now, "apple", 12.2f, "manufacturer 1",  newCat3));
        Item item2 = itemRepository.save(new Item(now, now, "orange", 23.2f, "manufacturer 2", newCat3));
        Item item3 = itemRepository.save(new Item(now, now, "USB data cable", 44.2f, "manufacturer 1", newCat5));
        Item item4 = itemRepository.save(new Item(now, now, "stand", 55.2f, "manufacturer 2", newCat5));
        Image image1 = imageRepository.save(new Image(now, now, "files/apple.png", "apple", "png", item1));
        Image image2 = imageRepository.save(new Image(now, now, "files/orange.png", "orange", "png", item2));
        Image image3 = imageRepository.save(new Image(now, now, "files/usb_data_cable.png", "usb_data_cable", "png", item3));
        Image image4 = imageRepository.save(new Image(now, now, "files/stand.png", "stand", "png", item4));
        Privilege readPrivilege = privilegeRepository.save(new Privilege(now, now, PrivilegeType.READ_PRIVILEGE));
        Privilege writePrivilege = privilegeRepository.save(new Privilege(now, now, PrivilegeType.WRITE_PRIVILEGE));
        Role adminRole = new Role(now, now, RoleType.ROLE_ADMIN);
        adminRole.setPrivileges(List.of(readPrivilege, writePrivilege));
        adminRole = roleRepository.save(adminRole);
        Role userRole = new Role(now, now, RoleType.ROLE_USER);
        userRole.setPrivileges(List.of(readPrivilege, writePrivilege));
        userRole = roleRepository.save(userRole);
        AppUser adminUser  = new AppUser(now, now, "ken123", passwordEncoder.encode("123"), "ken123@xts-shop.com", null, "ken123");
        AppUser user = new AppUser(now, now, "marry123", passwordEncoder.encode("123"), "marry123@xts-shop.com", null, "marry123");
        adminUser.setRoles(List.of(adminRole));
        user.setRoles(List.of(userRole));
        userRepository.save(adminUser);
        userRepository.save(user);


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
            log.info("Preloading " + image1);
            log.info("Preloading " + image2);
            log.info("Preloading " + image3);
            log.info("Preloading " + image4);
            log.info("Preloading " + adminUser);
        };
    }
}
