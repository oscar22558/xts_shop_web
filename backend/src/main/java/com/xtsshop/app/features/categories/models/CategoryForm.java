package com.xtsshop.app.features.categories.models;

import lombok.Setter;

import javax.annotation.Nullable;

@Setter
public class CategoryForm{
    @Nullable
    private String name;
    @Nullable
    private Long parentId;
    public CategoryRequest toRequest(){
        return new CategoryRequest(
                name,
                parentId
        );
    }
}
