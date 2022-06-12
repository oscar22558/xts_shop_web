package com.xtsshop.app.domain.request.categories;

import com.xtsshop.app.datasource.requests.items.ItemSortingField;
import com.xtsshop.app.datasource.requests.SortingDirection;
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
    private ItemSortingField sortingField;
    private SortingDirection sortingDirection;

    public ItemSortingField getSortingField() {
        return sortingField;
    }

    public void setSortingField(String sortingField) {
        this.sortingField = ItemSortingField.PRICE;
    }

    public SortingDirection getSortingDirection() {
        return sortingDirection;
    }

    public void setSortingDirection(String sortingDirection) {
        if(sortingDirection.toLowerCase(Locale.ROOT) == "asc"){
            this.sortingDirection = SortingDirection.ASC;
        }else{
            this.sortingDirection = SortingDirection.DESC;
        }
    }
}
