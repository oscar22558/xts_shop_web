package com.xtsshop.app.assembler;

import com.xtsshop.app.advice.exception.RecordNotFoundException;
import com.xtsshop.app.controller.categories.CategoriesCRUDController;
import com.xtsshop.app.controller.categories.items.ItemsController;
import com.xtsshop.app.db.entities.Category;
import com.xtsshop.app.viewmodel.CategoryModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.lang.NonNullApi;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class CategoryModelAssembler extends AbstractModelAssembler<CategoryModel, Category> {

    private ItemModelAssembler itemModelAssembler;

    @Autowired
    public void setStorageService(ItemModelAssembler itemModelAssembler) {
        this.itemModelAssembler = itemModelAssembler;
    }
    @Override
    public EntityModel<CategoryModel> toModel(Category entity) {
        try {
            return EntityModel.of(
                CategoryModel.from(entity, this, itemModelAssembler),
                linkTo(methodOn(CategoriesCRUDController.class).one(entity.getId())).withSelfRel(),
                linkTo(methodOn(CategoriesCRUDController.class).create(null)).withRel("create"),
                linkTo(methodOn(CategoriesCRUDController.class).update(entity.getId(), null)).withRel("update"),
                linkTo(methodOn(CategoriesCRUDController.class).delete(entity.getId())).withRel("delete"),
                linkTo(methodOn(CategoriesCRUDController.class).all()).withRel("all"),
                linkTo(methodOn(ItemsController.class).all(entity.getId())).withRel("items"),
                linkTo(methodOn(ItemsController.class).create(entity.getId(), null)).withRel("createItems")
            );
        } catch (RecordNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public CollectionModel<EntityModel<CategoryModel>> toCollectionModel(Iterable<? extends Category> entities) {
        return super.toCollectionModel(entities)
                .add(linkTo(methodOn(CategoriesCRUDController.class).all()).withRel("create"))
                .add(linkTo(methodOn(CategoriesCRUDController.class).all()).withSelfRel());
    }
}
