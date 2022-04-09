package com.xtsshop.app.request;

import com.xtsshop.app.db.entities.Category;
import com.xtsshop.app.util.DateTimeUtil;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nullable;
import java.sql.Date;
import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
public class CategoryRequest implements Request<Category> {
    @Nullable
    private String name;
    @Nullable
    private Long parentId;

    public CategoryRequest(@Nullable String name, @Nullable Long parentId) {
        this.name = name;
        this.parentId = parentId;
    }
    public Category toEntity(){
        Date now = new DateTimeUtil().now();
        Category category = new Category(
            now,
            now,
            name,
            parentId == null ? null : new Category(parentId)
        );
        category.setSubCategories(new ArrayList<>());
        category.setItems(new ArrayList<>());
        return category;
    }
    public Category update(Category original){
        original.setName(name != null ? name: original.getName());
        original.setParent(parentId != null ? new Category(parentId) : original.getParent());
        return original;
    }
}
