package com.xtsshop.app.domain.request.categories;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
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
}
