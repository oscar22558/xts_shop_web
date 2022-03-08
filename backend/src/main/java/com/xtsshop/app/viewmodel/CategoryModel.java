package com.xtsshop.app.viewmodel;

import com.xtsshop.app.assembler.CategoryModelAssembler;
import com.xtsshop.app.assembler.ItemModelAssembler;
import com.xtsshop.app.db.entities.Category;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.EntityModel;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;
@Getter
@Setter
@NoArgsConstructor
public class CategoryModel implements AbstractViewModel {
    static Logger logger = LoggerFactory.getLogger(CategoryModel.class);
    private long id;

    private Date created_at;

    private Date updated_at;

    private String name;

    private List<EntityModel<CategoryModel>> subCategories;

    private List<EntityModel<ItemModel>> items;

    public CategoryModel(long id, Date created_at, Date updated_at, String name, List<EntityModel<CategoryModel>> subCategories, List<EntityModel<ItemModel>> items) {
        this.id = id;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.name = name;
        this.subCategories = subCategories;
        this.items = items;
    }

    public static CategoryModel from(
        Category categoryEntity,
        CategoryModelAssembler assembler,
        ItemModelAssembler itemAssembler
    ){
//        CategoryModelAssembler assembler = new CategoryModelAssembler();
//        ItemModelAssembler itemAssembler = new ItemModelAssembler();
        logger.info("=====================categortModel from=======================");
       logger.info("subCategory.size: "+ (categoryEntity.getSubCategories() != null ? categoryEntity.getSubCategories().stream().count() : -1));
        List<EntityModel<CategoryModel>> subModels = categoryEntity.getSubCategories().stream().map(assembler::toModel).collect(Collectors.toList());
        List<EntityModel<ItemModel>> itemModels = categoryEntity.getItems().stream().map(itemAssembler::toModel).collect(Collectors.toList());
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