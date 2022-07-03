package com.xtsshop.app.domain.request.brands;

import lombok.Getter;

@Getter
public class BrandCreateRequest {
    private String name;

    public BrandCreateRequest(String name) {
        this.name = name;
    }
}
