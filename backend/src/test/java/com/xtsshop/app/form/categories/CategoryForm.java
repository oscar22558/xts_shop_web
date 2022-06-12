package com.xtsshop.app.form.categories;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.annotation.Nullable;

@Setter
@Getter
@NoArgsConstructor
public class CategoryForm {

    @Nullable
    private String name;
    @Nullable
    private Long parentId;

    public CategoryForm(@Nullable String name, @Nullable Long parentId) {
        this.name = name;
        this.parentId = parentId;
    }
}
