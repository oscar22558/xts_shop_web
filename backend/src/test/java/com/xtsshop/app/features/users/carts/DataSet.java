package com.xtsshop.app.features.users.carts;

import com.xtsshop.app.db.entities.AppUser;
import com.xtsshop.app.db.entities.Item;
import com.xtsshop.app.db.repositories.ItemJpaRepository;
import com.xtsshop.app.db.repositories.UserJpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component("tests.http.carts.DataSet")
public class DataSet {
    private UserJpaRepository userJpaRepository;
    private ItemJpaRepository itemJpaRepository;

    public DataSet(UserJpaRepository userJpaRepository, ItemJpaRepository itemJpaRepository) {
        this.userJpaRepository = userJpaRepository;
        this.itemJpaRepository = itemJpaRepository;
    }

    @Transactional
    public void insert(){
        AppUser user = getUser();
        List<Item> items = getItems();
        items.forEach(user::addItemToCart);
        userJpaRepository.save(user);
    }
    private AppUser getUser(){
        return userJpaRepository.findUserByUsername("marry123");
    }
    private List<Item> getItems(){
        return itemJpaRepository.findAll().subList(1, 3);
    }
}
