package com.xtsshop.app.features.categories.items;

import com.xtsshop.app.db.entities.Category;
import com.xtsshop.app.db.entities.Item;
import com.xtsshop.app.db.repositories.CategoryJpaRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class RecursiveItemSearcher {
    private List<Long> topLevelCategoryIds;
    private CategoryJpaRepository categoryJpaRepository;

    public RecursiveItemSearcher(CategoryJpaRepository categoryJpaRepository) {
        this.categoryJpaRepository = categoryJpaRepository;
    }
    public void setTopLevelCategoryIds(List<Long> topLevelCategoryIds) {
        this.topLevelCategoryIds = topLevelCategoryIds;
    }

    public List<Item> search(){
        return categoryJpaRepository.findAllById(topLevelCategoryIds)
                .stream()
                .map(this::getItemsRecursively)
                .reduce(new ArrayList<>(), (acc, current)->{
                    acc.addAll(current);
                    return acc;
                });
    }
    public List<Item> getItemsRecursively(Category category){
        List<Item> items = category.getItems();
        List<Item> itemsOfChild = category.getSubCategories()
                .stream()
                .flatMap((subCategory)->getItemsRecursively(subCategory).stream())
                .collect(Collectors.toList());
        items.addAll(itemsOfChild);
        return items;
    }
}
