package com.xtsshop.app.service.users.carts;

import com.xtsshop.app.advice.exception.UnAuthorizationException;
import com.xtsshop.app.db.entities.AppUser;
import com.xtsshop.app.db.entities.Item;
import com.xtsshop.app.db.repositories.ItemRepository;
import com.xtsshop.app.db.repositories.UserRepository;
import com.xtsshop.app.request.users.cart.CartListItemRequest;
import com.xtsshop.app.request.users.cart.CartRequest;
import com.xtsshop.app.service.auth.UserIdentityService;
import com.xtsshop.app.service.users.TargetUserService;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CartsService {
    private ItemRepository itemRepository;
    private UserIdentityService userIdentityService;
    private UserRepository userRepository;
    public CartsService(ItemRepository itemRepository, UserIdentityService userIdentityService, UserRepository userRepository) {
        this.itemRepository = itemRepository;
        this.userIdentityService = userIdentityService;
        this.userRepository = userRepository;
    }

    public List<Item> addItems(CartRequest request) {
        AppUser user = userIdentityService.getUser();
        List<Item> items = itemRepository.findAllById(request.getItemIds());
        items.forEach(user::addItemToCart);
        List<Item> sortedItems = sortItems(new ArrayList<>(userRepository.save(user).getCart()));
        return new ArrayList<>(sortedItems);
    }
    public List<Item> removeItems(CartRequest request) {
        AppUser user = userIdentityService.getUser();
        List<Item> items = itemRepository.findAllById(request.getItemIds());
        items.forEach(user::removeItemFromCart);
        List<Item> sortedItems = sortItems(new ArrayList<>(userRepository.save(user).getCart()));
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
