package com.xtsshop.app.viewmodel.builder;

import com.xtsshop.app.viewmodel.BrandViewModel;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class BrandViewModelBuilder {
    private Long id;
    private String name;

    public BrandViewModelBuilder setId(Long id) {
        this.id = id;
        return this;
    }

    public BrandViewModelBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public BrandViewModel build(){
        return new BrandViewModel(id, name);
    }
}
