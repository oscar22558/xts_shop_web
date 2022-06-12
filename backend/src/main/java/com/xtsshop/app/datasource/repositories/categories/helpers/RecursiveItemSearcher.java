package com.xtsshop.app.datasource.repositories.categories.helpers;

import com.xtsshop.app.db.entities.Category;
import com.xtsshop.app.db.entities.Item;
import com.xtsshop.app.db.repositories.CategoryRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class RecursiveItemSearcher {
    private List<Long> topLevelCategoryIds;
    private CategoryRepository categoryRepository;

    public RecursiveItemSearcher(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
    public void setTopLevelCategoryIds(List<Long> topLevelCategoryIds) {
        this.topLevelCategoryIds = topLevelCategoryIds;
    }

    public List<Item> search(){
        return categoryRepository.findAllById(topLevelCategoryIds)
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
