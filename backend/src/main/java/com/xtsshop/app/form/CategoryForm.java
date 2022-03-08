package com.xtsshop.app.form;

import com.xtsshop.app.db.entities.Category;
import com.xtsshop.app.request.CategoryRequest;
import lombok.Setter;

import javax.annotation.Nullable;

@Setter
public class CategoryForm implements Form<CategoryRequest, Category> {
    @Nullable
    private String name;
    @Nullable
    private Long parentId;
    @Override
    public CategoryRequest toRequest(){
        CategoryRequest request = new CategoryRequest();
        request.setName(name);
        request.setParentId(parentId);
        return request;
    }
}
