package com.xtsshop.app.request;

import com.xtsshop.app.db.entities.Category;
import com.xtsshop.app.util.DateTimeUtil;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.annotation.Nullable;

@Getter
@Setter
@NoArgsConstructor
public class CategoryForm implements Form<Category> {
    @Nullable
    private String name;
    @Nullable
    private Long parentId;

    public CategoryForm(@Nullable String name, @Nullable Long parentId) {
        this.name = name;
        this.parentId = parentId;
    }
    public Category toEntity(){
        return new Category(
            new DateTimeUtil().now(),
            name,
            parentId == null ? null : new Category(parentId)
        );
    }
    public Category update(Category original){
        original.setName(name != null ? name: original.getName());
        original.setParent(parentId != null ? new Category(parentId) : original.getParent());
        return original;
    }
}
