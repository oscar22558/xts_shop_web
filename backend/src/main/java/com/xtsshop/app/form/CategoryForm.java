package com.xtsshop.app.form;

import com.xtsshop.app.db.entities.Category;
import com.xtsshop.app.request.CategoryRequest;
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
