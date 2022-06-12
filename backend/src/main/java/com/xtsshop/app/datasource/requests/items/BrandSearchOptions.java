package com.xtsshop.app.datasource.requests.items;

import lombok.Getter;

import java.util.List;

@Getter
public class BrandSearchOptions {
    private List<Long> brandIds;

    public BrandSearchOptions(List<Long> brandIds) {
        this.brandIds = brandIds;
    }
}
