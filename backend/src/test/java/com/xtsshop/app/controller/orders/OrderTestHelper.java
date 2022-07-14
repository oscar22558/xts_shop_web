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
    private OrderRepository orderRepository;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private ItemRepository itemRepository;
    private OrderWithPaymentData orderWithPaymentData;
    private AddressRepository addressRepository;

    public OrderTestHelper(OrderRepository orderRepository, UserRepository userRepository, RoleRepository roleRepository, ItemRepository itemRepository, OrderWithPaymentData orderWithPaymentData, AddressRepository addressRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.itemRepository = itemRepository;
        this.orderWithPaymentData = orderWithPaymentData;
        this.addressRepository = addressRepository;
    }

    public String getRoute() {
        return route;
    }
    public String getOneOrderRoute(){ return oneOrderRoute; }
    public void insertData(){
        AppUser user = userRepository.findUserByUsername("marry123");
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
        AppUser user = userRepository.findUserByUsername("marry123");
        user = insertAddressForUser(user);
        orderWithPaymentData.insertOrderWithPaymentForUser(user);
    }

    public AppUser insertAddressForUser(AppUser user){
        Date now = new DateTimeHelper().now();
        Address address = addressRepository.save(new Address(now, now, "China", "Hong Kong", "HKU MB166", null, null, user));
        user.getAddresses().add(address);
        return userRepository.save(user);
    }
    public AppUser insertOrderForUser(AppUser user){
        Date now = new DateTimeHelper().now();
        List<Item> itemList = itemRepository.findAll();
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
        orderRepository.save(order);
        return userRepository.findUserByUsername(user.getUsername());
    }
    public AppUser insertNewUser(){
        Date now = new DateTimeHelper().now();
        Role role = roleRepository.findByName(RoleType.ROLE_USER.name());
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        AppUser user = new AppUser(now, now, "mario123", passwordEncoder.encode("123"), "marioy123@xts-shop.com", "28735601");
        user.setRoles(Set.of(role));
        user.setOrders(new ArrayList<>());
        user = userRepository.save(user);
        user.setAddresses(insertAddressForNewUser(user));

        return userRepository.findUserByUsername("mario123");
    }
    public List<Address> insertAddressForNewUser(AppUser user){
        Date now = new DateTimeHelper().now();
        Address address = addressRepository.save(new Address(now, now, "China", "Hong Kong", "HKU MB155", null, null, user));
        List<Address> addresses = new ArrayList<>();
        addresses.add(address);
        return addresses;
    }

    public Order insertOrderForNewUser(AppUser user){
        Date now = new DateTimeHelper().now();
        List<Item> itemList = itemRepository.findAll();
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
        return orderRepository.save(order);
    }

    public Order getOrderOfUser(){
        return userRepository.findUserByUsername("marry123").getOrders().get(0);
    }

    public Optional<Order> getLatestOrder(){
        int count = (int) orderRepository.count();
        return Optional.ofNullable(orderRepository.findAll().get(count - 1));
    }
    public Item getLatestOrderItemId(int index) throws Exception {
        long orderedItemId = getLatestOrder().orElseThrow(()->new Exception("No order"))
                .getOrderedItems().get(index)
                .getItem()
                .getId();
        return itemRepository.findById(orderedItemId).orElseThrow(()->new Exception("No such item"));
    }
    public void updatePriceOfItem(){
        Item item = itemRepository.findAll().get(1);
        PriceHistory priceHistory = new PriceHistoryBuilder()
                .setItem(item)
                .setValue(25.5f)
                .build();
        item.setPrice(25.5f);
        item.getPriceHistories().add(priceHistory);
        itemRepository.save(item);
    }
    public AppUser getUser(){
        return userRepository.findUserByUsername("marry123");
    }
    public AppUser getNewUser(){
       return userRepository.findUserByUsername("mario123");
    }
}

