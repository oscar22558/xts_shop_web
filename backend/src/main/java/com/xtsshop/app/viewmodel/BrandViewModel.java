package com.xtsshop.app.viewmodel;

import lombok.Getter;

@Getter
public class BrandViewModel {
    private Long id;
    private String name;

    public BrandViewModel(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
