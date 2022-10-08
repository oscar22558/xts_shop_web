package com.xtsshop.app.features.items.models;

import com.xtsshop.app.AbstractModelAssembler;
import com.xtsshop.app.advices.exception.RecordNotFoundException;
import com.xtsshop.app.features.items.ItemsController;
import com.xtsshop.app.db.entities.Item;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ItemModelAssembler extends
        AbstractModelAssembler<ItemRepresentationModel, Item> {

    @Override
    public EntityModel<ItemRepresentationModel> toModel(Item entity) {
        try {
            return getEntityModel(entity);
        } catch (RecordNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    private EntityModel<ItemRepresentationModel> getEntityModel(Item entity) {
        return EntityModel.of(
                getItemViewModel(entity),
                linkTo(methodOn(ItemsController.class).one(entity.getId())).withSelfRel(),
                linkTo(methodOn(ItemsController.class).all(null)).withRel("create"),
                linkTo(methodOn(ItemsController.class).update(entity.getId(), null)).withRel("update"),
                linkTo(methodOn(ItemsController.class).updateImage(entity.getId(), null)).withRel("updateImage"),
                linkTo(methodOn(ItemsController.class).delete(entity.getId())).withRel("delete"),
                linkTo(methodOn(ItemsController.class).all(null)).withRel("all")
        );
    }
    private ItemRepresentationModel getItemViewModel(Item entity){
        return new ItemRepresentationModel(entity);
    }
}
