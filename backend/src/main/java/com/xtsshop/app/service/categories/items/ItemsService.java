package com.xtsshop.app.service.categories.items;

import com.xtsshop.app.db.entities.Category;
import com.xtsshop.app.db.entities.Item;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@NoArgsConstructor
public class ItemsService {

    private static final Logger log = LoggerFactory.getLogger(ItemsService.class);

    @Setter
    private List<Category> categories;

    public List<Item> all(){
        List<List<Item>> items = categories
                .stream()
                .map(Category::getItems).collect(Collectors.toList());
        log.info(categories.get(0).toString());
        log.info(items.toString());
        items.stream().forEach(itemGroup->{
//            itemGroup.stream().forEach(item->{
//                log.info(item.toString());
//            });
        });
        return categories
                .stream()
                .map(this::getItemsRecursively)
                .reduce(new ArrayList<>(), (acc, current)->{
                    acc.addAll(current);
                    return acc;
                });
    }

    private List<Item> getItemsRecursively(Category category){
        List<Item> items = category.getItems();
        List<List<Item>> itemsOfChild = category.getSubCategories()
                .stream()
                .map(this::getItemsRecursively)
                .collect(Collectors.toList());
        itemsOfChild.forEach(items::addAll);
        return items;
    }
}
