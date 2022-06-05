package com.xtsshop.app.assembler;

import com.xtsshop.app.advice.exception.RecordNotFoundException;
import com.xtsshop.app.controller.categories.CategoriesController;
import com.xtsshop.app.controller.categories.items.ItemsController;
import com.xtsshop.app.db.entities.Category;
import com.xtsshop.app.viewmodel.CategoryModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Component;

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
                linkTo(methodOn(CategoriesController.class).one(entity.getId())).withSelfRel(),
                linkTo(methodOn(CategoriesController.class).create(null)).withRel("create"),
                linkTo(methodOn(CategoriesController.class).update(entity.getId(), null)).withRel("update"),
                linkTo(methodOn(CategoriesController.class).delete(entity.getId())).withRel("delete"),
                linkTo(methodOn(CategoriesController.class).all()).withRel("all"),
                linkTo(methodOn(ItemsController.class).listAll(entity.getId())).withRel("items"),
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
                .add(linkTo(methodOn(CategoriesController.class).all()).withRel("create"))
                .add(linkTo(methodOn(CategoriesController.class).all()).withSelfRel());
    }
}
