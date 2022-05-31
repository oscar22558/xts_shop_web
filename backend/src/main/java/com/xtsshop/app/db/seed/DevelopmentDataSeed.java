package com.xtsshop.app.db.seed;

import com.xtsshop.app.db.entities.*;
import com.xtsshop.app.db.entities.builder.*;
import com.xtsshop.app.db.repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

public class DevelopmentDataSeed {

    private CategoryRepository repository;
    private ItemRepository itemRepository;
    private ImageRepository imageRepository;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PrivilegeRepository privilegeRepository;
    private BrandRepository brandRepository;

    public DevelopmentDataSeed(CategoryRepository repository, ItemRepository itemRepository, ImageRepository imageRepository, UserRepository userRepository, RoleRepository roleRepository, PrivilegeRepository privilegeRepository, BrandRepository brandRepository) {
        this.repository = repository;
        this.itemRepository = itemRepository;
        this.imageRepository = imageRepository;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.privilegeRepository = privilegeRepository;
        this.brandRepository = brandRepository;
    }

    public void insertData(){
        Date now = new Date(Calendar.getInstance().getTimeInMillis());
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        createCategories();
        createBrands();
        createItems(now);
        createRoles(now);
        createAdminUser(now, passwordEncoder);
        createNormalUser(now, passwordEncoder);
    }

    public void createCategories(){
        Category newCategory1 = repository.save(new CategoryBuilder().setName("food").setParent(null).build());
        Category newCategory2 = repository.save(new CategoryBuilder().setName("tools").setParent(null).build());
        repository.save(new CategoryBuilder().setName("fruit").setParent(newCategory1).build());
        repository.save(new CategoryBuilder().setName("meat").setParent(newCategory1).build());
        Category newCategory3 = repository.save(new CategoryBuilder().setName("mobile device peripheral").setParent(newCategory2).build());
        repository.save(new CategoryBuilder().setName("drawing tools").setParent(newCategory2).build());
        repository.save(new CategoryBuilder().setName("USB cable").setParent(newCategory3).build());
        repository.save(new CategoryBuilder().setName("Mobile phone charger").setParent(newCategory3).build());
    }
    public void createBrands(){
        brandRepository.save(new BrandBuilder().setName("Brand 1").build());
        brandRepository.save(new BrandBuilder().setName("Brand 2").build());
        brandRepository.save(new BrandBuilder().setName("Brand 3").build());
    }
    public void createItems(Date now){
        Category category3 = repository.findAll().get(2);
        Category category5 = repository.findAll().get(4);
        List<Brand> brand = brandRepository.findAll();
        Item item1 = new ItemBuilder()
                .setName("apple")
                .addPriceHistory(new PriceHistoryBuilder().setValue(12.2f).build())
                .setManufacturer("manufacturer 1")
                .setCategory(category3)
                .setStock(100)
                .setBrand(brand.get(0))
                .build();
        Item item2 = new ItemBuilder()
                .setName("orange")
                .addPriceHistory(new PriceHistoryBuilder().setValue(23.2f).build())
                .setManufacturer("manufacturer 2")
                .setCategory(category3)
                .setStock(101)
                .setBrand(brand.get(1))
                .build();
        Item item3 = new ItemBuilder()
                .setName("USB data cable")
                .addPriceHistory(new PriceHistoryBuilder().setValue(44.2f).build())
                .setManufacturer("manufacturer 1")
                .setCategory(category5)
                .setStock(102)
                .setBrand(brand.get(2))
                .build();
        Item item4 = new ItemBuilder().setName("stand")
                .addPriceHistory(new PriceHistoryBuilder().setValue(55.2f).build())
                .setManufacturer("manufacturer 2")
                .setCategory(category5)
                .setStock(103)
                .setBrand(brand.get(2))
                .build();
        item1.setImage(new Image(now, now, "storage/develop/images/apple.png", "apple", "png", item1));
        item2.setImage(new Image(now, now, "storage/develop/images/orange.png", "orange", "png", item2));
        item3.setImage(new Image(now, now, "storage/develop/images/usb_data_cable.png", "usb_data_cable", "png", item3));
        item4.setImage(new Image(now, now, "storage/develop/images/stand.png", "stand", "png", item4));
        itemRepository.save(item1);
        itemRepository.save(item2);
        itemRepository.save(item3);
        itemRepository.save(item4);
    }
    public void createAdminUser(Date now, BCryptPasswordEncoder passwordEncoder){
        Role role = roleRepository.findByName(RoleType.ROLE_ADMIN.name());
        AppUser user  = new AppUser(now, now, "ken123", passwordEncoder.encode("123"), "ken123@xts-shop.com", "23245566");
        user.setRoles(Set.of(role));
        userRepository.save(user);

    }
    public void createNormalUser(Date now, BCryptPasswordEncoder passwordEncoder){
        Role role = roleRepository.findByName(RoleType.ROLE_USER.name());
        AppUser user = new AppUser(now, now, "marry123", passwordEncoder.encode("123"), "marry123@xts-shop.com", "28735601");
        user.setRoles(Set.of(role));

        List<Address> addresses = new ArrayList<>();
        addresses.add(new AddressBuilder()
                .setCountry("China")
                .setCity("Hong Kong")
                .setUser(user)
                .setRow1("HKU MB166")
                .build()
        );
        user.setAddresses(addresses);
        userRepository.save(user);
    }
    public void createRoles(Date now){
        Privilege readPrivilege = privilegeRepository.save(new Privilege(now, now, PrivilegeType.READ_PRIVILEGE));
        Privilege writePrivilege = privilegeRepository.save(new Privilege(now, now, PrivilegeType.WRITE_PRIVILEGE));
        Role adminRole = new Role(now, now, RoleType.ROLE_ADMIN);
        adminRole.setPrivileges(Set.of(readPrivilege, writePrivilege));
        roleRepository.save(adminRole);
        Role userRole = new Role(now, now, RoleType.ROLE_USER);
        userRole.setPrivileges(Set.of(readPrivilege, writePrivilege));
        roleRepository.save(userRole);
    }
}
