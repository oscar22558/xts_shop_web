package com.xtsshop.app.repository.Categories;

import com.xtsshop.app.db.entities.Item;
import com.xtsshop.app.db.repositories.ItemRepository;
import com.xtsshop.app.repository.Categories.Helpers.RecursiveCategorySearcher;
import com.xtsshop.app.repository.requests.Categories.BrandSearchOptions;
import com.xtsshop.app.repository.requests.Categories.PriceSearchOptions;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CategoryItemRepo {
    private ItemRepository itemRepository;
    private RecursiveCategorySearcher categorySearcher;

    private List<Long> categoryIds;
    private Optional<PriceSearchOptions> priceSearchOptions;
    private List<Long> brandIds;

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

    public List<Item> findItemsUnderCategories(){
        if(priceSearchOptions.isPresent()) {
            return findItemsUnderCategoriesWithPriceFilter();
        }else{
            return findItemsUnderCategoriesWithoutPriceFilter();
        }
    }
    public List<Item> findItemsUnderCategoriesWithPriceFilter(){
        float maxPrice = priceSearchOptions.flatMap((option)->Optional.of(option.getMaxPrice())).orElse(10000f);
        float minPrice = priceSearchOptions.flatMap((option)->Optional.of(option.getMinPrice())).orElse(0f);
        if(isBrandFilterSet()){
            return itemRepository.findAllWithBrandIdAndPrice(categoryIds, brandIds, maxPrice, minPrice);
        }
        return itemRepository.findAllWithPrice(categoryIds, maxPrice, minPrice);
    }
    public List<Item> findItemsUnderCategoriesWithoutPriceFilter(){
        if(isBrandFilterSet()){
            return itemRepository.findAllWithBrandId(categoryIds, brandIds);
        }
        return itemRepository.findAllById(categoryIds);
    }

    private boolean isBrandFilterSet(){
        return brandIds.size() > 0;
    }
}
