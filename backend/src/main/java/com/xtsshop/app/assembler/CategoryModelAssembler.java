package com.xtsshop.app.assembler;

import com.xtsshop.app.controller.CategoriesController;
import com.xtsshop.app.entities.Category;
import com.xtsshop.app.entities.CategoryModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class CategoryModelAssembler implements
        RepresentationModelAssembler<Category, EntityModel<CategoryModel>> {
    @Override
    public EntityModel<CategoryModel> toModel(Category entity) {
        return EntityModel.of(
            CategoryModel.from(entity),
            linkTo(methodOn(CategoriesController.class).one(entity.getId())).withSelfRel(),
            linkTo(methodOn(CategoriesController.class).all()).withRel("categories"),
        );
    }
}
