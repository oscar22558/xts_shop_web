package com.xtsshop.app.db.seed;

import com.xtsshop.app.db.entities.*;
import com.xtsshop.app.db.entities.builder.*;
import com.xtsshop.app.db.repositories.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

@Component
@Transactional
public class DevelopmentDataSeed {

    private CategoryRepository repository;
    private ItemRepository itemRepository;
    private ImageRepository imageRepository;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PrivilegeRepository privilegeRepository;
    private BrandRepository brandRepository;
    private PriceHistoryRepository priceHistoryRepository;

    public DevelopmentDataSeed(CategoryRepository repository, ItemRepository itemRepository, ImageRepository imageRepository, UserRepository userRepository, RoleRepository roleRepository, PrivilegeRepository privilegeRepository, BrandRepository brandRepository, PriceHistoryRepository priceHistoryRepository) {
        this.repository = repository;
        this.itemRepository = itemRepository;
        this.imageRepository = imageRepository;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.privilegeRepository = privilegeRepository;
        this.brandRepository = brandRepository;
        this.priceHistoryRepository = priceHistoryRepository;
    }

    public void insertData(){
        Date now = new Date(Calendar.getInstance().getTimeInMillis());
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        createCategories();
        createBrands();
        createItems();
        createImagesForItems();
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
        repository.save(new CategoryBuilder().setName("Mobile phone stand").setParent(newCategory3).build());
    }
    public void createBrands(){
        brandRepository.save(new BrandBuilder().setName("Brand 1").build());
        brandRepository.save(new BrandBuilder().setName("Brand 2").build());
        brandRepository.save(new BrandBuilder().setName("Brand 3").build());
    }

    public void createItems(){
        Category category3 = repository.findAll().get(2);
        Category usbCableCategory = repository.findAll().get(6);
        Category phoneStandCategory = repository.findAll().get(8);
        List<Brand> brands = brandRepository.findAll();
        Item item1 = itemRepository.save(new ItemBuilder()
                .setName("apple")
                .setManufacturer("manufacturer 1")
                .setCategory(category3)
                .setStock(100)
                .setBrand(brands.get(0))
                .setPrice(12.2f)
                .build()
        );
        setPriceForItem(item1, 12.2f);
        Item item2 = itemRepository.save(new ItemBuilder()
                .setName("orange")
                .setManufacturer("manufacturer 2")
                .setCategory(category3)
                .setStock(101)
                .setBrand(brands.get(1))
                .setPrice(23.2f)
                .build()
        );
        setPriceForItem(item2, 23.2f);
        Item item3 = itemRepository.save(new ItemBuilder()
                .setName("2M USB 3.0 data cable")
                .setManufacturer("manufacturer 1")
                .setCategory(usbCableCategory)
                .setStock(102)
                .setBrand(brands.get(2))
                .setPrice(44.2f)
                .build()
        );
        setPriceForItem(item3, 44.2f);
        Item item4 = itemRepository.save(new ItemBuilder().setName("ABC stand")
                .setManufacturer("manufacturer 2")
                .setCategory(phoneStandCategory)
                .setStock(103)
                .setBrand(brands.get(2))
                .setPrice(55.2f)
                .build()
        );
        setPriceForItem(item4, 55.2f);
    }
    public Item setPriceForItem(Item item, float price){
        PriceHistory priceHistory = new PriceHistoryBuilder().setValue(price).setItem(item).build();
        priceHistoryRepository.save(priceHistory);
        item.getPriceHistories().add(priceHistory);
        return itemRepository.save(item);
    }
    public void createImagesForItems(){
        List<Item> items = itemRepository.findAll();
        imageRepository.save(
            new ImageBuilder()
                .setPath("storage/develop/images/apple.png")
                .setFileName("apple")
                .setExtension("png")
                .setItem(items.get(0))
                .build()
        );
        imageRepository.save(
                new ImageBuilder()
                        .setPath("storage/develop/images/orange.png")
                        .setFileName("orange")
                        .setExtension("png")
                        .setItem(items.get(1)).build()
        );
        imageRepository.save(
                new ImageBuilder()
                .setPath("storage/develop/images/usb_data_cable.png")
                .setFileName("usb_data_cable")
                .setExtension("png")
                .setItem(items.get(2))
                .build()
        );
        imageRepository.save(
                new ImageBuilder()
                        .setPath("storage/develop/images/stand.png")
                        .setFileName("stand")
                        .setExtension("png")
                        .setItem(items.get(3))
                        .build()
        );
    }
    public void createAdminUser(Date now, BCryptPasswordEncoder passwordEncoder){
        Role role = roleRepository.findByName(RoleType.ROLE_ADMIN.name());
        Role adminRole = roleRepository.findByName(RoleType.ROLE_USER.name());
        AppUser user  = new AppUser(now, now, "ken123", passwordEncoder.encode("123"), "ken123@xts-shop.com", "23245566");
        user.setRoles(Set.of(role, adminRole));
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
        user.setOrders(List.of());
        user.setCart(Set.of());
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
