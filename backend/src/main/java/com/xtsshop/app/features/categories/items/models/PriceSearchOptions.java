package com.xtsshop.app.features.categories.items.models;

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
