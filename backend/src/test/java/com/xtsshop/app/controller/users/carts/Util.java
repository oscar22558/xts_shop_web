package com.xtsshop.app.controller.users.carts;

import com.xtsshop.app.db.entities.Item;
import com.xtsshop.app.db.repositories.ItemRepository;
import com.xtsshop.app.db.repositories.UserRepository;
import lombok.Getter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Getter
@Component("tests.http.carts.Util")
public class Util {
    private ItemRepository itemRepository;
    private UserRepository userRepository;
    public Util(ItemRepository itemRepository, UserRepository userRepository) {
        this.itemRepository = itemRepository;
        this.userRepository = userRepository;
    }

    private String route = "/api/users/cart";
    public List<Item> getItems(){
        return itemRepository.findAll().subList(1, 3);
    }

    @Transactional
    public List<Item> getItemsInCart(String username){
        return new ArrayList<>(userRepository.findUserByUsername(username).getCart());
    }
}
