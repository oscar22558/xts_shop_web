package com.xtsshop.app.http.users.orders;

import com.xtsshop.app.db.entities.*;
import com.xtsshop.app.db.entities.builder.OrderBuilder;
import com.xtsshop.app.db.entities.payment.PaymentType;
import com.xtsshop.app.db.repositories.ItemRepository;
import com.xtsshop.app.db.repositories.OrderRepository;
import com.xtsshop.app.db.repositories.RoleRepository;
import com.xtsshop.app.db.repositories.UserRepository;
import com.xtsshop.app.form.orders.OrderCreateForm;
import com.xtsshop.app.form.orders.OrderedItemCreateForm;
import com.xtsshop.app.form.orders.PaymentCreateForm;
import com.xtsshop.app.util.DateTimeUtil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class Util {

    private String route = "/api/users/orders";
    private ItemRepository itemRepository;
    private UserRepository userRepository;
    private OrderRepository orderRepository;
    private RoleRepository roleRepository;
    public Util(ItemRepository itemRepository, UserRepository userRepository, OrderRepository orderRepository, RoleRepository roleRepository) {
        this.itemRepository = itemRepository;
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
        this.roleRepository = roleRepository;
    }

    public String getRoute() {
        return route;
    }

    public OrderCreateForm buildCreatFormWithPayment(){
        Item item1 = itemRepository.findAll().get(0);
        Item item2 = itemRepository.findAll().get(1);

        AppUser user = userRepository.findUserByUsername("marry123");
        OrderCreateForm form = new OrderCreateForm();
        form.setUsername("marry123");
        form.setAddressId(user.getAddresses().get(0).getId());
        PaymentCreateForm payment = new PaymentCreateForm();
        payment.setPaymentType(PaymentType.CREDIT_CARD);
        payment.setBankCode("012");
        payment.setCardNum("1235132901842385824");
        payment.setHolderName("Chan Tai Man");
        payment.setPaidTotal(377.2f);

        OrderedItemCreateForm orderedItem = new OrderedItemCreateForm();
        orderedItem.setItemId(item1.getId());
        orderedItem.setQuantity(10);

        OrderedItemCreateForm orderedItem2 = new OrderedItemCreateForm();
        orderedItem2.setItemId(item2.getId());
        orderedItem2.setQuantity(11);

        List<OrderedItemCreateForm> orderedItems = new ArrayList<>();
        orderedItems.add(orderedItem);
        orderedItems.add(orderedItem2);
        form.setPayment(payment);
        form.setOrderedItems(orderedItems);
        return form;
    }

    public OrderCreateForm buildCreatFormWithoutPayment(){
        Item item1 = itemRepository.findAll().get(0);
        Item item2 = itemRepository.findAll().get(1);

        AppUser user = userRepository.findUserByUsername("marry123");
        OrderCreateForm form = new OrderCreateForm();
        form.setUsername("marry123");
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
       int count = (int) orderRepository.count();
       return Optional.ofNullable(orderRepository.findAll().get(count - 1));
    }

    public void insertOrderForUser(){
        AppUser user = userRepository.findUserByUsername("marry123");
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
        userRepository.save(user);
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
}
