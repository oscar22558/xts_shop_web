package com.xtsshop.app.http.orders;

import com.xtsshop.app.db.entities.*;
import com.xtsshop.app.db.entities.builder.OrderBuilder;
import com.xtsshop.app.db.repositories.ItemRepository;
import com.xtsshop.app.db.repositories.OrderRepository;
import com.xtsshop.app.db.repositories.RoleRepository;
import com.xtsshop.app.db.repositories.UserRepository;
import com.xtsshop.app.util.DateTimeUtil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component("tests.http.orders.Util")
public class Util {

    private String route = "/api/orders";
    private String getRequestRoute = "/api/orders/{orderId}";
    private OrderRepository orderRepository;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private ItemRepository itemRepository;

    public Util(OrderRepository orderRepository, UserRepository userRepository, RoleRepository roleRepository, ItemRepository itemRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.itemRepository = itemRepository;
    }

    public String getRoute() {
        return route;
    }
    public String getGetRequestRoute(){ return getRequestRoute; }
    public void insertData(){
        AppUser user = userRepository.findUserByUsername("marry123");
        user = insertAddressForUser(user);
        insertOrderForUser(user);
        AppUser newUser = insertNewUser();
    }
    public AppUser insertAddressForUser(AppUser user){
        Date now = new DateTimeUtil().now();
        Address address = new Address(now, now, "China", "Hong Kong", "HKU MB166", null, null, user);
        user.getAddresses().add(address);
        return userRepository.save(user);
    }
    public AppUser insertOrderForUser(AppUser user){
        Date now = new DateTimeUtil().now();
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
        user.getOrders().add(order);
        return userRepository.save(user);
    }
    public AppUser insertNewUser(){
        Date now = new DateTimeUtil().now();
        Role role = roleRepository.findByName(RoleType.ROLE_USER.name());
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        AppUser user = new AppUser(now, now, "mario123", passwordEncoder.encode("123"), "marioy123@xts-shop.com", "28735601");
        user.setRoles(Set.of(role));
        List<Address> addresses = new ArrayList<>();
        addresses.add(new Address(now, now, "China", "Hong Kong", "HKU MB155", null, null, user));
        user.setAddresses(addresses);
        user.setOrders(new ArrayList<>());
        return insertOrderForNewUser(user);
//        return userRepository.save(user);
    }
    public AppUser insertOrderForNewUser(AppUser user){
        Date now = new DateTimeUtil().now();
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
        return userRepository.save(user);
    }

    public Order getOrderOfUser(){
        return userRepository.findUserByUsername("marry123").getOrders().get(0);
    }
}

