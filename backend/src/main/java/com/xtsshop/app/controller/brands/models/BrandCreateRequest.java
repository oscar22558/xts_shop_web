package com.xtsshop.app.controller.brands.models;

import lombok.Getter;

@Getter
public class BrandCreateRequest {
    private String name;

    public BrandCreateRequest(String name) {
        this.name = name;
    }
}
