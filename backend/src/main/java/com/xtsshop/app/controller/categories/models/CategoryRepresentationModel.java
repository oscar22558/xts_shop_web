package com.xtsshop.app.controller.categories.models;

import com.xtsshop.app.AbstractRepresentationModel;
import com.xtsshop.app.controller.items.models.ItemRepresentationModel;
import com.xtsshop.app.controller.items.models.ItemModelAssembler;
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
public class CategoryRepresentationModel implements AbstractRepresentationModel {
    static Logger logger = LoggerFactory.getLogger(CategoryRepresentationModel.class);
    private long id;

    private Date created_at;

    private Date updated_at;

    private String name;

    private List<EntityModel<CategoryRepresentationModel>> subCategories;

    private List<EntityModel<ItemRepresentationModel>> items;

    public CategoryRepresentationModel(long id, Date created_at, Date updated_at, String name, List<EntityModel<CategoryRepresentationModel>> subCategories, List<EntityModel<ItemRepresentationModel>> items) {
        this.id = id;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.name = name;
        this.subCategories = subCategories;
        this.items = items;
    }

    public static CategoryRepresentationModel from(
        Category categoryEntity,
        CategoryModelAssembler assembler,
        ItemModelAssembler itemAssembler
    ){
//        CategoryModelAssembler assembler = new CategoryModelAssembler();
//        ItemModelAssembler itemAssembler = new ItemModelAssembler();
        logger.info("=====================categortModel from=======================");
       logger.info("subCategory.size: "+ (categoryEntity.getSubCategories() != null ? categoryEntity.getSubCategories().stream().count() : -1));
        List<EntityModel<CategoryRepresentationModel>> subModels = categoryEntity.getSubCategories().stream().map(assembler::toModel).collect(Collectors.toList());
        List<EntityModel<ItemRepresentationModel>> itemModels = categoryEntity.getItems().stream().map(itemAssembler::toModel).collect(Collectors.toList());
        return new CategoryRepresentationModel(
                categoryEntity.getId(),
                categoryEntity.getCreatedAt(),
                categoryEntity.getUpdatedAt(),
                categoryEntity.getName(),
                subModels,
                itemModels
        );
    }
}