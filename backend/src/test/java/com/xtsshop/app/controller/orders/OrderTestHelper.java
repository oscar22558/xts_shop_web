package com.xtsshop.app.controller.orders;

import com.xtsshop.app.db.entities.*;
import com.xtsshop.app.db.entities.builder.OrderBuilder;
import com.xtsshop.app.db.entities.builder.PriceHistoryBuilder;
import com.xtsshop.app.db.repositories.*;
import com.xtsshop.app.helpers.DateTimeHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Transactional
@Component
public class OrderTestHelper {

    private String route = "/api/orders";
    private String oneOrderRoute = "/api/orders/{orderId}";
    private OrderJpaRepository orderJpaRepository;
    private UserJpaRepository userJpaRepository;
    private RoleJpaRepository roleJpaRepository;
    private ItemJpaRepository itemJpaRepository;
    private OrderWithPaymentData orderWithPaymentData;
    private AddressJpaRepository addressJpaRepository;
    private PriceHistoryJpaRepository priceHistoryJpaRepository;

    public OrderTestHelper(OrderJpaRepository orderJpaRepository, UserJpaRepository userJpaRepository, RoleJpaRepository roleJpaRepository, ItemJpaRepository itemJpaRepository, OrderWithPaymentData orderWithPaymentData, AddressJpaRepository addressJpaRepository, PriceHistoryJpaRepository priceHistoryJpaRepository) {
        this.orderJpaRepository = orderJpaRepository;
        this.userJpaRepository = userJpaRepository;
        this.roleJpaRepository = roleJpaRepository;
        this.itemJpaRepository = itemJpaRepository;
        this.orderWithPaymentData = orderWithPaymentData;
        this.addressJpaRepository = addressJpaRepository;
        this.priceHistoryJpaRepository = priceHistoryJpaRepository;
    }

    public String getRoute() {
        return route;
    }
    public String getOneOrderRoute(){ return oneOrderRoute; }

    public void insertData(){
        AppUser user = userJpaRepository.findUserByUsername("marry123");
        user = insertAddressForUser(user);
        insertOrderForUser(user);
        AppUser newUser = insertNewUser();
        updatePriceOfItem();
    }
    public void insertDataWithNewUserOrder(){
        insertData();
        insertOrderForNewUser(getNewUser());
    }

    public void insertDataForTestOrderWithPayment(){
        AppUser user = userJpaRepository.findUserByUsername("marry123");
        user = insertAddressForUser(user);
        orderWithPaymentData.insertOrderWithPaymentForUser(user);
    }

    public AppUser insertAddressForUser(AppUser user){
        Date now = new DateTimeHelper().now();
        Address address = new Address(now, now, "China", "Hong Kong", "Hong Kong", "Central and Western", null, "HKU MB166");
        address = addressJpaRepository.save(address);
        user.getAddresses().add(address);
        return user;
    }
    public AppUser insertOrderForUser(AppUser user){
        Date now = new DateTimeHelper().now();
        List<Item> itemList = itemJpaRepository.findAll();
        List<OrderedItem> orderedItems = new ArrayList<>();
        orderedItems.add(new OrderedItem(now, now, itemList.get(0), 2));
        orderedItems.add(new OrderedItem(now, now, itemList.get(1), 7));
        orderedItems.add(new OrderedItem(now, now, itemList.get(3), 5));
        Order order = new OrderBuilder()
                .setUser(user)
                .setShippingAddress(user.getAddresses().get(0))
                .setStatus(OrderStatus.WAITING_PAYMENT)
                .setOrderedItems(orderedItems)
                .build();
        orderJpaRepository.save(order);
        return userJpaRepository.findUserByUsername(user.getUsername());
    }
    public AppUser insertNewUser(){
        Date now = new DateTimeHelper().now();
        Role role = roleJpaRepository.findByName(RoleType.ROLE_USER.name());
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        AppUser user = new AppUser(now, now, "mario123", passwordEncoder.encode("123"), "marioy123@xts-shop.com", "28735601");
        user.setRoles(Set.of(role));
        user.setOrders(new ArrayList<>());
        user = userJpaRepository.save(user);
        user.setAddresses(insertAddressForNewUser(user));

        return userJpaRepository.findUserByUsername("mario123");
    }
    public List<Address> insertAddressForNewUser(AppUser user){
        Date now = new DateTimeHelper().now();
        Address address = new Address(now, now, "China", "Hong Kong", "Hong Kong", "Central and Western", null, "HKU MB155");
        address = addressJpaRepository.save(address);
        List<Address> addresses = new ArrayList<>();
        addresses.add(address);
        return addresses;
    }

    public Order insertOrderForNewUser(AppUser user){
        Date now = new DateTimeHelper().now();
        List<Item> itemList = itemJpaRepository.findAll();
        List<OrderedItem> orderedItems = new ArrayList<>();
        orderedItems.add(new OrderedItem(now, now, itemList.get(1), 3));
        orderedItems.add(new OrderedItem(now, now, itemList.get(3), 4));
        orderedItems.add(new OrderedItem(now, now, itemList.get(2), 10));
        Order order = new OrderBuilder()
                .setUser(user)
                .setShippingAddress(user.getAddresses().get(0))
                .setStatus(OrderStatus.WAITING_PAYMENT)
                .setOrderedItems(orderedItems)
                .build();
        user.getOrders().add(order);
        return orderJpaRepository.save(order);
    }

    public Order getOrderOfUser(){
        return userJpaRepository.findUserByUsername("marry123").getOrders().get(0);
    }

    public Optional<Order> getLatestOrder(){
        int count = (int) orderJpaRepository.count();
        return Optional.ofNullable(orderJpaRepository.findAll().get(count - 1));
    }
    public Item getLatestOrderItemId(int index) throws Exception {
        long orderedItemId = getLatestOrder().orElseThrow(()->new Exception("No order"))
                .getOrderedItems().get(index)
                .getItem()
                .getId();
        return itemJpaRepository.findById(orderedItemId).orElseThrow(()->new Exception("No such item"));
    }
    public void updatePriceOfItem(){
        Item item = itemJpaRepository.findAll().get(1);
        PriceHistory priceHistory = new PriceHistoryBuilder()
                .setItem(item)
                .setValue(25.5f)
                .build();
        priceHistory = priceHistoryJpaRepository.save(priceHistory);
        item.setPrice(25.5f);
        item.getPriceHistories().add(priceHistory);
    }
    public AppUser getUser(){
        return userJpaRepository.findUserByUsername("marry123");
    }
    public AppUser getNewUser(){
       return userJpaRepository.findUserByUsername("mario123");
    }
}

