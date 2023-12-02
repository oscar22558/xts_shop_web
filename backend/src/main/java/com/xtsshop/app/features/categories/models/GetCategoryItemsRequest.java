package com.xtsshop.app.features.categories.models;

import com.xtsshop.app.features.categories.items.models.ItemSortingMethod;
import com.xtsshop.app.features.categories.items.models.SortingDirection;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Getter
@Setter
@NoArgsConstructor
public class GetCategoryItemsRequest {
    private Optional<Long> categoryId;
    private List<Long> categoryIds;
    private Optional<Float> minPrice;
    private Optional<Float> maxPrice;
    private List<Long> brandIds;
    private ItemSortingMethod sortingMethod;
    private SortingDirection sortingDirection;

    public ItemSortingMethod getSortingMethod() {
        return sortingMethod;
    }

    public void setSortingMethod(Optional<String> sortingField) {
        this.sortingMethod = ItemSortingMethod.PRICE;
    }

    public SortingDirection getSortingDirection() {
        return sortingDirection;
    }

    public void setSortingDirection(Optional<String> sortingDirection) {
        Optional<String> lowerCaseDirection = sortingDirection.flatMap((direction)->Optional.of(direction.toLowerCase(Locale.ROOT)));
        if(lowerCaseDirection.orElse("asc").equals("asc")){
            this.sortingDirection = SortingDirection.ASC;
        }else{
            this.sortingDirection = SortingDirection.DESC;
        }
    }
}
