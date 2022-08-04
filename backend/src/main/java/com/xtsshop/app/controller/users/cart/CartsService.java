package com.xtsshop.app.controller.users.cart;

import com.xtsshop.app.controller.authentication.UserIdentityService;
import com.xtsshop.app.controller.users.cart.models.CartRequest;
import com.xtsshop.app.db.entities.AppUser;
import com.xtsshop.app.db.entities.Item;
import com.xtsshop.app.db.repositories.ItemJpaRepository;
import com.xtsshop.app.db.repositories.UserJpaRepository;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CartsService {
    private ItemJpaRepository itemJpaRepository;
    private UserIdentityService userIdentityService;
    private UserJpaRepository userJpaRepository;
    public CartsService(ItemJpaRepository itemJpaRepository, UserIdentityService userIdentityService, UserJpaRepository userJpaRepository) {
        this.itemJpaRepository = itemJpaRepository;
        this.userIdentityService = userIdentityService;
        this.userJpaRepository = userJpaRepository;
    }

    public List<Item> addItems(CartRequest request) {
        AppUser user = userIdentityService.getUser();
        List<Item> items = itemJpaRepository.findAllById(request.getItemIds());
        items.forEach(user::addItemToCart);
        List<Item> sortedItems = sortItems(new ArrayList<>(userJpaRepository.save(user).getCart()));
        return new ArrayList<>(sortedItems);
    }
    public List<Item> removeItems(CartRequest request) {
        AppUser user = userIdentityService.getUser();
        List<Item> items = itemJpaRepository.findAllById(request.getItemIds());
        items.forEach(user::removeItemFromCart);
        List<Item> sortedItems = sortItems(new ArrayList<>(userJpaRepository.save(user).getCart()));
        return new ArrayList<>(sortedItems);
    }
    public List<Item> listItems() {
        AppUser user = userIdentityService.getUser();
        Set<Item> items = user.getCart();
        return new ArrayList<>(items);
    }

    private List<Item> sortItems(List<Item> items){
        return items.stream()
                .sorted((item, t1) -> (int) (item.getId()-t1.getId()))
                .collect(Collectors.toList());
    }
}
