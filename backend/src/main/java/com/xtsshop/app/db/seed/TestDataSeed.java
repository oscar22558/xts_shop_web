package com.xtsshop.app.db.seed;

import com.xtsshop.app.db.entities.*;
import com.xtsshop.app.db.entities.builder.*;
import com.xtsshop.app.db.repositories.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.*;

@Component
@Transactional
public class TestDataSeed {

    private CategoryJpaRepository repository;
    private ItemJpaRepository itemJpaRepository;
    private ImageJpaRepository imageJpaRepository;
    private UserJpaRepository userJpaRepository;
    private RoleJpaRepository roleJpaRepository;
    private PrivilegeJpaRepository privilegeJpaRepository;
    private BrandJpaRepository brandJpaRepository;
    private PriceHistoryJpaRepository priceHistoryJpaRepository;

    public TestDataSeed(CategoryJpaRepository repository, ItemJpaRepository itemJpaRepository, ImageJpaRepository imageJpaRepository, UserJpaRepository userJpaRepository, RoleJpaRepository roleJpaRepository, PrivilegeJpaRepository privilegeJpaRepository, BrandJpaRepository brandJpaRepository, PriceHistoryJpaRepository priceHistoryJpaRepository) {
        this.repository = repository;
        this.itemJpaRepository = itemJpaRepository;
        this.imageJpaRepository = imageJpaRepository;
        this.userJpaRepository = userJpaRepository;
        this.roleJpaRepository = roleJpaRepository;
        this.privilegeJpaRepository = privilegeJpaRepository;
        this.brandJpaRepository = brandJpaRepository;
        this.priceHistoryJpaRepository = priceHistoryJpaRepository;
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
        brandJpaRepository.save(new BrandBuilder().setName("Brand 1").build());
        brandJpaRepository.save(new BrandBuilder().setName("Brand 2").build());
        brandJpaRepository.save(new BrandBuilder().setName("Brand 3").build());
    }

    public void createItems(){
        Category category3 = repository.findAll().get(2);
        Category usbCableCategory = repository.findAll().get(6);
        Category phoneStandCategory = repository.findAll().get(8);
        List<Brand> brands = brandJpaRepository.findAll();
        Item item1 = itemJpaRepository.save(new ItemBuilder()
                .setName("apple")
                .setManufacturer("manufacturer 1")
                .setCategory(category3)
                .setStock(100)
                .setBrand(brands.get(0))
                .setPrice(12.2f)
                .build()
        );
        setPriceForItem(item1, 12.2f);
        Item item2 = itemJpaRepository.save(new ItemBuilder()
                .setName("orange")
                .setManufacturer("manufacturer 2")
                .setCategory(category3)
                .setStock(101)
                .setBrand(brands.get(1))
                .setPrice(23.2f)
                .build()
        );
        setPriceForItem(item2, 23.2f);
        Item item3 = itemJpaRepository.save(new ItemBuilder()
                .setName("2M USB 3.0 data cable")
                .setManufacturer("manufacturer 1")
                .setCategory(usbCableCategory)
                .setStock(102)
                .setBrand(brands.get(2))
                .setPrice(44.2f)
                .build()
        );
        setPriceForItem(item3, 44.2f);
        Item item4 = itemJpaRepository.save(new ItemBuilder().setName("ABC stand")
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
        priceHistoryJpaRepository.save(priceHistory);
        item.getPriceHistories().add(priceHistory);
        return itemJpaRepository.save(item);
    }
    public void createImagesForItems(){
        List<Item> items = itemJpaRepository.findAll();
        imageJpaRepository.save(
            new ImageBuilder()
                .setUri("/storage/develop/images/apple.jpg")
                .setFileName("apple")
                .setExtension("png")
                .setItem(items.get(0))
                .build()
        );
        imageJpaRepository.save(
                new ImageBuilder()
                        .setUri("/storage/develop/images/orange.jpg")
                        .setFileName("orange")
                        .setExtension("jpg")
                        .setItem(items.get(1)).build()
        );
        imageJpaRepository.save(
                new ImageBuilder()
                .setUri("/storage/develop/images/usb-cable.jpg")
                .setFileName("usb-cable")
                .setExtension("jpg")
                .setItem(items.get(2))
                .build()
        );
        imageJpaRepository.save(
                new ImageBuilder()
                        .setUri("/storage/develop/images/mobile-phone-stand.jpg")
                        .setFileName("mobile-phone-stand")
                        .setExtension("jpg")
                        .setItem(items.get(3))
                        .build()
        );
    }
    public void createAdminUser(Date now, BCryptPasswordEncoder passwordEncoder){
        Role role = roleJpaRepository.findByName(RoleType.ROLE_ADMIN.name());
        Role adminRole = roleJpaRepository.findByName(RoleType.ROLE_USER.name());
        AppUser user  = new AppUser(now, now, "ken123", passwordEncoder.encode("123"), "ken123@xts-shop.com", "23245566");
        user.setRoles(Set.of(role, adminRole));
        userJpaRepository.save(user);
    }
    public void createNormalUser(Date now, BCryptPasswordEncoder passwordEncoder){
        Role role = roleJpaRepository.findByName(RoleType.ROLE_USER.name());
        AppUser user = new AppUser(now, now, "marry123", passwordEncoder.encode("123"), "marry123@xts-shop.com", "28735601");
        user.setRoles(Set.of(role));
        Address address = new Address(
                now,
                now,
                "China",
                "Hong Kong",
                "Sai Ying Pun",
                "Hong Kong",
                "HKU MB166",
                ""
        );
        address.setUser(user);
        List<Address> addresses = new ArrayList<>();
        addresses.add(address);

        user.setAddresses(addresses);
        user.setOrders(new ArrayList<>());
        user.setCart(new HashSet<>());
        userJpaRepository.save(user);
    }
    public void createRoles(Date now){
        Privilege readPrivilege = privilegeJpaRepository.save(new Privilege(now, now, PrivilegeType.READ_PRIVILEGE));
        Privilege writePrivilege = privilegeJpaRepository.save(new Privilege(now, now, PrivilegeType.WRITE_PRIVILEGE));
        Role adminRole = new Role(now, now, RoleType.ROLE_ADMIN);
        adminRole.setPrivileges(Set.of(readPrivilege, writePrivilege));
        roleJpaRepository.save(adminRole);
        Role userRole = new Role(now, now, RoleType.ROLE_USER);
        userRole.setPrivileges(Set.of(readPrivilege, writePrivilege));
        roleJpaRepository.save(userRole);
    }
}
