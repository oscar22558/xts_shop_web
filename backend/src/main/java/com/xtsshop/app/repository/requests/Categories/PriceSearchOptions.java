package com.xtsshop.app.repository.requests.Categories;

import lombok.Getter;

@Getter
public class PriceSearchOptions {
    private Float minPrice;
    private Float maxPrice;
    public PriceSearchOptions(Float minPrice, Float maxPrice) {
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
    }
}
