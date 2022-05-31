package com.xtsshop.app.db.entities.builder;
import com.xtsshop.app.db.entities.Category;
import com.xtsshop.app.db.entities.Item;
import com.xtsshop.app.util.DateTimeUtil;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class CategoryBuilder {
    private String name;
    private Category parent;
    private List<Category> subCategories;
    private List<Item> items;
    public CategoryBuilder(){
        subCategories = new ArrayList<>();
        items = new ArrayList<>();
    }
    public CategoryBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public CategoryBuilder setParent(Category parent) {
        this.parent = parent;
        return this;
    }

    public CategoryBuilder setSubCategories(List<Category> subCategories) {
        this.subCategories = subCategories;
        return this;
    }

    public CategoryBuilder setItems(List<Item> items) {
        this.items = items;
        return this;
    }
    public Category build(){
        Date now = new DateTimeUtil().now();
        Category category = new Category();
        category.setParent(parent);
        category.setName(name);
        category.setSubCategories(subCategories);
        category.setItems(items);
        category.setCreatedAt(now);
        category.setUpdatedAt(now);
        return category;
    }
}
