package com.xtsshop.app.repository.Categories;

import com.xtsshop.app.db.entities.Category;
import com.xtsshop.app.db.entities.Item;
import com.xtsshop.app.db.repositories.CategoryRepository;
import com.xtsshop.app.db.repositories.ItemRepository;
import com.xtsshop.app.repository.requests.Categories.BrandSearchOptions;
import com.xtsshop.app.repository.requests.Categories.PriceSearchOptions;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class CategoryItemRepo {
    private CategoryRepository categoryRepository;
    private ItemRepository itemRepository;

    public CategoryItemRepo(CategoryRepository categoryRepository, ItemRepository itemRepository) {
        this.categoryRepository = categoryRepository;
        this.itemRepository = itemRepository;
    }

    //TODO: select appropriate query in repo
    public List<Item> findItemsByTopLevelCategoryIds(List<Long> topLevelCategoryIds, Optional<PriceSearchOptions> priceSearchOptions, Optional<BrandSearchOptions> brandSearchOption){
        List<Long> categoryIds = findAllSubcategoryIdsByTopLevelIds(topLevelCategoryIds);
        List<Long> brandIds = brandSearchOption.flatMap((option)->Optional.of(option.getBrandIds())).orElse(List.of());
        float maxPrice = priceSearchOptions.flatMap((option)->Optional.of(option.getMaxPrice())).orElse(10000f);
        float minPrice = priceSearchOptions.flatMap((option)->Optional.of(option.getMinPrice())).orElse(0f);
        if(priceSearchOptions.isPresent() && brandSearchOption.isPresent()){
            return itemRepository.findAllWithBrandIdAndPrice(categoryIds, brandIds, maxPrice, minPrice);
        }else if(priceSearchOptions.isPresent() && brandSearchOption.isEmpty()){
            return itemRepository.findAllWithBrandId(categoryIds, brandIds);
        }else if (priceSearchOptions.isEmpty() && brandSearchOption.isPresent()) {
            return itemRepository.findAllWithPrice(categoryIds, maxPrice, minPrice);
        } else{
             return itemRepository.findAllById(categoryIds);
        }
    }
    public List<Item> findItemsByTopLevelCategoryIds(List<Long> categoryIds){
        return categoryRepository.findAllById(categoryIds)
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

    public List<Long> findAllSubcategoryIdsByTopLevelIds(List<Long> categoryIds){
        List<Category> categories = categoryRepository.findAllById(categoryIds);
        return categories.stream()
                .flatMap((category)-> findCategoryIdsRecursively(category).stream())
                .collect(Collectors.toList());
    }
    public List<Long> findCategoryIdsRecursively(Category category){
        List<Long> ids = new ArrayList<>(List.of(category.getId()));
        List<Long> subCategoryIds = category.getSubCategories()
                .stream()
                .flatMap((subCategory)->findCategoryIdsRecursively(subCategory).stream())
                .collect(Collectors.toList());
        ids.addAll(subCategoryIds);
        return ids;
    }
}
