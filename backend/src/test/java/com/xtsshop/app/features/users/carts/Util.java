package com.xtsshop.app.features.users.carts;

import com.xtsshop.app.db.entities.Item;
import com.xtsshop.app.db.repositories.ItemJpaRepository;
import com.xtsshop.app.db.repositories.UserJpaRepository;
import lombok.Getter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Getter
@Component("tests.http.carts.Util")
public class Util {
    private ItemJpaRepository itemJpaRepository;
    private UserJpaRepository userJpaRepository;
    public Util(ItemJpaRepository itemJpaRepository, UserJpaRepository userJpaRepository) {
        this.itemJpaRepository = itemJpaRepository;
        this.userJpaRepository = userJpaRepository;
    }

    private String route = "/api/users/cart";
    public List<Item> getItems(){
        return itemJpaRepository.findAll().subList(1, 3);
    }

    @Transactional
    public List<Item> getItemsInCart(String username){
        return new ArrayList<>(userJpaRepository.findUserByUsername(username).getCart());
    }
}
