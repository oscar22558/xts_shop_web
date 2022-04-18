package com.xtsshop.app.request;

import com.xtsshop.app.db.entities.Category;
import com.xtsshop.app.db.entities.builder.CategoryBuilder;
import com.xtsshop.app.util.DateTimeUtil;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nullable;
import javax.validation.constraints.Null;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Optional;

@Getter
@Setter
@NoArgsConstructor
public class CategoryRequest{
    private Optional<String> name;
    private Optional<Long> parentId;

    public CategoryRequest(@Nullable String name, @Nullable Long parentId) {
        this.name = Optional.ofNullable(name);
        this.parentId = Optional.ofNullable(parentId);
    }
    public Category toEntity(Optional<Category> parent){
        return new CategoryBuilder()
                .setItems(new ArrayList<>())
                .setName(name.orElseThrow(NullPointerException::new))
                .setSubCategories(new ArrayList<>())
                .setParent(parent.orElse(null))
                .build();
    }
    public Category update(Category original,  Optional<Category> newParent){
        Date now = new DateTimeUtil().now();
        original.setName(name.orElseGet(original::getName));
        original.setParent(newParent.orElse(original.getParent()));
        if(
            name.isPresent() ||
            parentId.isPresent()
        )
            original.setUpdatedAt(now);
        return original;
    }
}
