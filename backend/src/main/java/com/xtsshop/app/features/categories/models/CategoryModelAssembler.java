package com.xtsshop.app.features.categories.models;

import com.xtsshop.app.AbstractModelAssembler;
import com.xtsshop.app.advices.exception.RecordNotFoundException;
import com.xtsshop.app.features.categories.CategoriesController;
import com.xtsshop.app.features.categories.items.ItemsController;
import com.xtsshop.app.db.entities.Category;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class CategoryModelAssembler extends AbstractModelAssembler<CategoryRepresentationModel, Category> {
    @Override
    public EntityModel<CategoryRepresentationModel> toModel(Category entity) {
        try {
            Map<String, Long> values = new HashMap<>();
            values.put("categoryId", entity.getId());
            return EntityModel.of(
                new CategoryRepresentationModel(entity, this),
                linkTo(methodOn(CategoriesController.class).one(entity.getId())).withSelfRel(),
                linkTo(methodOn(CategoriesController.class).create(null)).withRel("create"),
                linkTo(methodOn(CategoriesController.class).update(entity.getId(), null)).withRel("update"),
                linkTo(methodOn(CategoriesController.class).delete(entity.getId())).withRel("delete"),
                linkTo(methodOn(CategoriesController.class).all()).withRel("all"),
                Link.of("/categories/{categoryId}/items").withRel("items").expand(values),
                linkTo(methodOn(ItemsController.class).create(entity.getId(), null)).withRel("createItems")
            );
        } catch (RecordNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public CollectionModel<EntityModel<CategoryRepresentationModel>> toCollectionModel(Iterable<? extends Category> entities) {
        return super.toCollectionModel(entities)
                .add(linkTo(methodOn(CategoriesController.class).all()).withRel("create"))
                .add(linkTo(methodOn(CategoriesController.class).all()).withSelfRel());
    }
}
