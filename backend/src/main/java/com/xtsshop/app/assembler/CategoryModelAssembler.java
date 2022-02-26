package com.xtsshop.app.assembler;

import com.xtsshop.app.controller.categories.CategoriesController;
import com.xtsshop.app.controller.categories.items.ItemsController;
import com.xtsshop.app.db.entities.Category;
import com.xtsshop.app.viewmodel.CategoryModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.lang.NonNullApi;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class CategoryModelAssembler extends AbstractModelAssembler<CategoryModel, Category> {
    @Override
    public EntityModel<CategoryModel> toModel(Category entity) {
        return EntityModel.of(
            CategoryModel.from(entity),
            linkTo(methodOn(CategoriesController.class).one(entity.getId())).withSelfRel(),
            linkTo(methodOn(CategoriesController.class).all()).withRel("all"),
            linkTo(methodOn(ItemsController.class).all(entity.getId()) ).withRel("items")

        );
    }
}
