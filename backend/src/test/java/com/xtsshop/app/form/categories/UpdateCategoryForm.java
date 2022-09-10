package com.xtsshop.app.form.categories;

import lombok.Getter;

@Getter
public class UpdateCategoryForm {
    private String name;
    private Long parentId;

    public UpdateCategoryForm(String name, Long parentId) {
        this.name = name;
        this.parentId = parentId;
    }
}
