package com.xtsshop.app.features.brands.models;

import lombok.Getter;

@Getter
public class BrandCreateRequest {
    private String name;

    public BrandCreateRequest(String name) {
        this.name = name;
    }
}
