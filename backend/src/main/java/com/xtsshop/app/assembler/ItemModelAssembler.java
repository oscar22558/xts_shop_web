package com.xtsshop.app.assembler;

import com.xtsshop.app.controller.CategoriesController;
import com.xtsshop.app.entities.Category;
import com.xtsshop.app.entities.Item;
import com.xtsshop.app.model.CategoryModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ItemModelAssembler implements
        RepresentationModelAssembler<Item, EntityModel<Item>> {
    @Override
    public EntityModel<Item> toModel(Item entity) {
        return EntityModel.of(
            entity,
            linkTo(methodOn(CategoriesController.class).one(entity.getId())).withSelfRel(),
            linkTo(methodOn(CategoriesController.class).all()).withRel("items")
        );
    }
}
