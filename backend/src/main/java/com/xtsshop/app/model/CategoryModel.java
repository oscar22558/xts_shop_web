package com.xtsshop.app.model;

import com.xtsshop.app.assembler.CategoryModelAssembler;
import com.xtsshop.app.assembler.ItemModelAssembler;
import com.xtsshop.app.entities.Category;
import com.xtsshop.app.entities.Item;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.EntityModel;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;
@Getter
@Setter
@NoArgsConstructor
public class CategoryModel {
    private long id;

    private Date created_at;

    private Date updated_at;

    private String name;

    private List<EntityModel<CategoryModel>> subCategories;

    private List<EntityModel<Item>> items;

    public CategoryModel(long id, Date created_at, Date updated_at, String name, List<EntityModel<CategoryModel>> subCategories, List<EntityModel<Item>> items) {
        this.id = id;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.name = name;
        this.subCategories = subCategories;
        this.items = items;
    }

    public CategoryModel(Date created_at, String name) {
        this.created_at = created_at;
        this.name = name;
    }

    public static CategoryModel from(Category categoryEntity){
        CategoryModelAssembler assembler = new CategoryModelAssembler();
        ItemModelAssembler itemAssembler = new ItemModelAssembler();
        List<EntityModel<CategoryModel>> subModels = categoryEntity.getSubCategories().stream().map(assembler::toModel).collect(Collectors.toList());
        List<EntityModel<Item>> itemModels = categoryEntity.getItems().stream().map(itemAssembler::toModel).collect(Collectors.toList());
        return new CategoryModel(
                categoryEntity.getId(),
                categoryEntity.getCreatedAt(),
                categoryEntity.getUpdatedAt(),
                categoryEntity.getName(),
                subModels,
                itemModels
        );
    }
}