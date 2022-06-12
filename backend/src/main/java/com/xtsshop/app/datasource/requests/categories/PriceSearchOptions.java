package com.xtsshop.app.datasource.requests.categories;

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
