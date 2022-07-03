package com.xtsshop.app.datasource.repositories.items;

import com.xtsshop.app.datasource.repositories.items.helpers.RecursiveCategorySearcher;
import com.xtsshop.app.datasource.requests.SortingDirection;
import com.xtsshop.app.db.entities.Item;
import com.xtsshop.app.db.repositories.ItemRepository;
import com.xtsshop.app.datasource.requests.items.BrandSearchOptions;
import com.xtsshop.app.datasource.requests.items.PriceSearchOptions;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class CategoryItemRepo {
    private ItemRepository itemRepository;
    private RecursiveCategorySearcher categorySearcher;

    private List<Long> categoryIds;
    private Optional<PriceSearchOptions> priceSearchOptions;
    private List<Long> brandIds;
    private SortingDirection sortingDirection;

    public CategoryItemRepo(ItemRepository itemRepository, RecursiveCategorySearcher categorySearcher) {
        this.itemRepository = itemRepository;
        this.categorySearcher = categorySearcher;
    }

    public void setTopLevelCategoryIds(List<Long> topLevelCategoryIds) {
        categorySearcher.setTopLevelCategoryIds(topLevelCategoryIds);
        this.categoryIds = categorySearcher.search();
    }

    public void setPriceSearchOptions(PriceSearchOptions priceSearchOptions) {
        this.priceSearchOptions = Optional.ofNullable(priceSearchOptions);
    }

    public void setBrandSearchOptions(BrandSearchOptions brandSearchOption) {
        brandIds = brandSearchOption == null ? List.of() : brandSearchOption.getBrandIds();
    }

    public void setSortingDirection(SortingDirection sortingDirection) {
        this.sortingDirection = sortingDirection;
    }

    public List<Item> findItemsUnderCategories(){
        List<Item> items = priceSearchOptions.isPresent()
            ? findItemsUnderCategoriesWithPriceFilter()
            : findItemsUnderCategoriesWithoutPriceFilter();
        return sortItems(items);
    }

    public List<Item> sortItems(List<Item> items){
        return items.stream().sorted((o1, o2) -> (int) ((o1.getPrice() - o2.getPrice()) * (
                    sortingDirection == SortingDirection.ASC ? 1 : -1
                )))
                .collect(Collectors.toList());
    }

    public List<Item> findItemsUnderCategoriesWithPriceFilter(){
        float maxPrice = priceSearchOptions.flatMap((option)->Optional.of(option.getMaxPrice())).orElse(10000f);
        float minPrice = priceSearchOptions.flatMap((option)->Optional.of(option.getMinPrice())).orElse(0f);
        if(isBrandFilterSet()){
            return itemRepository.findAllForCategoriesWithBrandIdAndPrice(categoryIds, brandIds, maxPrice, minPrice);
        }
        return itemRepository.findAllForCategoriesWithPrice(categoryIds, maxPrice, minPrice);
    }
    public List<Item> findItemsUnderCategoriesWithoutPriceFilter(){
        if(isBrandFilterSet()){
            return itemRepository.findAllForCategoriesWithBrandId(categoryIds, brandIds);
        }
        return itemRepository.findAllForCategories(categoryIds);
    }

    private boolean isBrandFilterSet(){
        return brandIds.size() > 0;
    }
}
