package com.xtsshop.app.http.carts;

import com.xtsshop.app.db.entities.AppUser;
import com.xtsshop.app.db.entities.Item;
import com.xtsshop.app.db.repositories.ItemRepository;
import com.xtsshop.app.db.repositories.UserRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component("tests.http.carts.DataSet")
public class DataSet {
    private UserRepository userRepository;
    private ItemRepository itemRepository;

    public DataSet(UserRepository userRepository, ItemRepository itemRepository) {
        this.userRepository = userRepository;
        this.itemRepository = itemRepository;
    }

    @Transactional
    public void insert(){
        AppUser user = getUser();
        List<Item> items = getItems();
        items.forEach(user::addItemToCart);
        userRepository.save(user);
    }
    private AppUser getUser(){
        return userRepository.findUserByUsername("marry123");
    }
    private List<Item> getItems(){
        return itemRepository.findAll().subList(1, 3);
    }
}
