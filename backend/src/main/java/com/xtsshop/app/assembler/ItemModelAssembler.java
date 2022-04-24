package com.xtsshop.app.assembler;

import com.xtsshop.app.advice.exception.RecordNotFoundException;
import com.xtsshop.app.controller.items.ItemsController;
import com.xtsshop.app.db.entities.Item;
import com.xtsshop.app.service.storage.StorageService;
import com.xtsshop.app.viewmodel.ItemModel;
import com.xtsshop.app.viewmodel.builder.ItemModelBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ItemModelAssembler extends
        AbstractModelAssembler<ItemModel, Item> {
    private StorageService storageService;

    @Autowired
    public void setStorageService(@Qualifier("ImageStorageService") StorageService storageService) {
        this.storageService = storageService;
    }

    @Override
    public EntityModel<ItemModel> toModel(Item entity) {
        try {
            return EntityModel.of(
                    new ItemModelBuilder().setItemEntity(entity).setStorageService(storageService).build(),
                    linkTo(methodOn(ItemsController.class).one(entity.getId())).withSelfRel(),
                    linkTo(methodOn(ItemsController.class).all()).withRel("create"),
                    linkTo(methodOn(ItemsController.class).update(entity.getId(), null)).withRel("update"),
                    linkTo(methodOn(ItemsController.class).updateImage(entity.getId(), null)).withRel("updateImage"),
                    linkTo(methodOn(ItemsController.class).delete(entity.getId())).withRel("delete"),
                    linkTo(methodOn(ItemsController.class).all()).withRel("all")
                    );
        } catch (RecordNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public EntityModel<ItemModel> toEntityModel(ItemModel model) {
        try {
            return EntityModel.of(
                    model,
                    linkTo(methodOn(ItemsController.class).one(model.getId())).withSelfRel(),
                    linkTo(methodOn(ItemsController.class).all()).withRel("create"),
                    linkTo(methodOn(ItemsController.class).update(model.getId(), null)).withRel("update"),
                    linkTo(methodOn(ItemsController.class).updateImage(model.getId(), null)).withRel("updateImage"),
                    linkTo(methodOn(ItemsController.class).delete(model.getId())).withRel("delete"),
                    linkTo(methodOn(ItemsController.class).all()).withRel("all")
            );
        } catch (RecordNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
