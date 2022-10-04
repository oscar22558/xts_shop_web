package com.xtsshop.app.features.orders;

import com.xtsshop.app.db.entities.*;
import com.xtsshop.app.features.orders.entitybuilders.OrderEntityBuilder;
import com.xtsshop.app.db.entities.builder.PriceHistoryBuilder;
import com.xtsshop.app.db.repositories.*;
import com.xtsshop.app.db.seed.TestDataSeed;
import com.xtsshop.app.features.orders.models.UserOrderCreateForm;
import com.xtsshop.app.features.users.cart.models.OrderedItemCreateForm;
import com.xtsshop.app.helpers.DateTimeHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Component
public class UserOrderTestHelper {

    private String route = "/api/users/{username}/orders";
    private ItemJpaRepository itemJpaRepository;
    private UserJpaRepository userJpaRepository;
    private OrderJpaRepository orderJpaRepository;
    private RoleJpaRepository roleJpaRepository;
    private AddressJpaRepository addressJpaRepository;
    private TestDataSeed seed;

    public UserOrderTestHelper(ItemJpaRepository itemJpaRepository, UserJpaRepository userJpaRepository, OrderJpaRepository orderJpaRepository, RoleJpaRepository roleJpaRepository, AddressJpaRepository addressJpaRepository, TestDataSeed seed) {
        this.itemJpaRepository = itemJpaRepository;
        this.userJpaRepository = userJpaRepository;
        this.orderJpaRepository = orderJpaRepository;
        this.roleJpaRepository = roleJpaRepository;
        this.addressJpaRepository = addressJpaRepository;
        this.seed = seed;
    }

    public String getRoute() {
        return route;
    }

    public UserOrderCreateForm buildCreatFormWithPayment(){
        Item item1 = itemJpaRepository.findAll().get(0);
        Item item2 = itemJpaRepository.findAll().get(1);

        AppUser user = userJpaRepository.findUserByUsername("marry123");
        UserOrderCreateForm form = new UserOrderCreateForm();
        form.setAddressId(user.getAddresses().get(0).getId());
        OrderedItemCreateForm orderedItem = new OrderedItemCreateForm();
        orderedItem.setItemId(item1.getId());
        orderedItem.setQuantity(10);

        OrderedItemCreateForm orderedItem2 = new OrderedItemCreateForm();
        orderedItem2.setItemId(item2.getId());
        orderedItem2.setQuantity(11);

        List<OrderedItemCreateForm> orderedItems = new ArrayList<>();
        orderedItems.add(orderedItem);
        orderedItems.add(orderedItem2);
        form.setOrderedItems(orderedItems);
        return form;
    }

    public UserOrderCreateForm buildCreatFormWithoutPayment(){
        Item item1 = itemJpaRepository.findAll().get(0);
        Item item2 = itemJpaRepository.findAll().get(1);

        AppUser user = userJpaRepository.findUserByUsername("marry123");
        UserOrderCreateForm form = new UserOrderCreateForm();
        form.setAddressId(user.getAddresses().get(0).getId());

        OrderedItemCreateForm orderedItem = new OrderedItemCreateForm();
        orderedItem.setItemId(item1.getId());
        orderedItem.setQuantity(10);

        OrderedItemCreateForm orderedItem2 = new OrderedItemCreateForm();
        orderedItem2.setItemId(item2.getId());
        orderedItem2.setQuantity(11);

        List<OrderedItemCreateForm> orderedItems = new ArrayList<>();
        orderedItems.add(orderedItem);
        orderedItems.add(orderedItem2);
        form.setOrderedItems(orderedItems);
        return form;
    }
    public Optional<Order> getLatestOrder(){
       int count = (int) orderJpaRepository.count();
       return Optional.ofNullable(orderJpaRepository.findAll().get(count - 1));
    }

    public void insertOrderForUser(){
        AppUser user = userJpaRepository.findUserByUsername("marry123");
        Date now = new DateTimeHelper().now();
        List<Item> itemList = itemJpaRepository.findAll();
        List<OrderedItem> orderedItems = new ArrayList<>();
        orderedItems.add(new OrderedItem(now, now, itemList.get(0), 2));
        orderedItems.add(new OrderedItem(now, now, itemList.get(1), 7));
        orderedItems.add(new OrderedItem(now, now, itemList.get(3), 5));
        Order order = new OrderEntityBuilder()
                .setUser(user)
                .setShippingAddress(user.getAddresses().get(0))
                .setStatus(OrderStatus.WAITING_PAYMENT)
                .setOrderedItems(orderedItems)
                .build();
        user.getOrders().add(order);
        orderJpaRepository.save(order);
    }

    public AppUser insertNewUser(){
        Date now = new DateTimeHelper().now();
        Role role = roleJpaRepository.findByName(RoleType.ROLE_USER.name());
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        AppUser user = new AppUser(now, now, "mario123", passwordEncoder.encode("123"), "marioy123@xts-shop.com", "28735601");
        user.setRoles(Set.of(role));

        Address address = new Address(now, now, "China", "Hong Kong", "Hong Kong", "Central and Western", null, "HKU MB166");
        address = addressJpaRepository.save(address);
        List<Address> addresses = new ArrayList<>();
        addresses.add(address);
        user.setAddresses(addresses);
        user.setOrders(new ArrayList<>());
        return userJpaRepository.save(user);
    }

    public Order insertOrderForNewUser(String username){
        AppUser user = userJpaRepository.findUserByUsername(username);
        Date now = new DateTimeHelper().now();
        List<Item> itemList = itemJpaRepository.findAll();
        List<OrderedItem> orderedItems = new ArrayList<>();
        orderedItems.add(new OrderedItem(now, now, itemList.get(1), 3));
        orderedItems.add(new OrderedItem(now, now, itemList.get(3), 4));
        orderedItems.add(new OrderedItem(now, now, itemList.get(2), 10));
        Order order = new OrderEntityBuilder()
                .setUser(user)
                .setShippingAddress(user.getAddresses().get(0))
                .setStatus(OrderStatus.WAITING_PAYMENT)
                .setOrderedItems(orderedItems)
                .build();
        user.getOrders().add(order);
        return orderJpaRepository.save(order);
    }
    public void updatePriceOfItem(){
        Item item = itemJpaRepository.findAll().get(1);
        PriceHistory priceHistory = new PriceHistoryBuilder()
                .setItem(item)
                .setValue(25.5f)
                .build();
        item.setPrice(25.5f);
        item.getPriceHistories().add(priceHistory);
        itemJpaRepository.save(item);
    }
    public AppUser findUserByUsername(String username){
        return userJpaRepository.findUserByUsername(username);
    }

    public void insertData(){
        seed.insertData();
    }
}
