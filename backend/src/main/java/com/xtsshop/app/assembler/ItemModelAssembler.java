package com.xtsshop.app.assembler;

import com.xtsshop.app.advice.exception.RecordNotFoundException;
import com.xtsshop.app.controller.items.ItemsController;
import com.xtsshop.app.db.entities.Item;
import com.xtsshop.app.service.storage.StorageProperties;
import com.xtsshop.app.service.storage.StorageService;
import com.xtsshop.app.viewmodel.ItemModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
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
                    ItemModel.from(entity, storageService),
                linkTo(methodOn(ItemsController.class).one(entity.getId())).withSelfRel(),
                linkTo(methodOn(ItemsController.class).all()).withRel("all")

            );
        } catch (RecordNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
