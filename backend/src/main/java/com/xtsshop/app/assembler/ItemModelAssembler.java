package com.xtsshop.app.assembler;

import com.xtsshop.app.controller.ItemsController;
import com.xtsshop.app.controller.categories.CategoriesController;
import com.xtsshop.app.db.entities.Item;
import com.xtsshop.app.viewmodel.ItemModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ItemModelAssembler implements
        RepresentationModelAssembler<Item, EntityModel<ItemModel>> {
    @Override
    public EntityModel<ItemModel> toModel(Item entity) {
        return EntityModel.of(
                ItemModel.from(entity),
            linkTo(methodOn(ItemsController.class).one(entity.getId())).withSelfRel(),
            linkTo(methodOn(ItemsController.class).all()).withRel("all")

        );
    }
}
