package com.xtsshop.app.assembler;

import com.xtsshop.app.advice.exception.RecordNotFoundException;
import com.xtsshop.app.controller.items.ItemsController;
import com.xtsshop.app.db.entities.Item;
import com.xtsshop.app.domain.service.storage.FilePathToUrlConverter;
import com.xtsshop.app.assembler.models.ItemRepresentationModel;
import com.xtsshop.app.assembler.models.builder.ItemModelBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ItemModelAssembler extends
        AbstractModelAssembler<ItemRepresentationModel, Item> {
    private FilePathToUrlConverter filePathToUrlConvertor;

    @Autowired
    public void setFilePathToUrlConvertor(FilePathToUrlConverter filePathToUrlConvertor) {
        this.filePathToUrlConvertor = filePathToUrlConvertor;
    }

    @Override
    public EntityModel<ItemRepresentationModel> toModel(Item entity) {
        try {
            return getEntityModel(entity);
        } catch (RecordNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public EntityModel<ItemRepresentationModel> toEntityModel(ItemRepresentationModel model) {
        try {
            return getEntityModel(model);
        } catch (RecordNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
    private EntityModel<ItemRepresentationModel> getEntityModel(Item entity) throws RecordNotFoundException {
        return EntityModel.of(
                getItemViewModel(entity),
                linkTo(methodOn(ItemsController.class).one(entity.getId())).withSelfRel(),
                linkTo(methodOn(ItemsController.class).all()).withRel("create"),
                linkTo(methodOn(ItemsController.class).update(entity.getId(), null)).withRel("update"),
                linkTo(methodOn(ItemsController.class).updateImage(entity.getId(), null)).withRel("updateImage"),
                linkTo(methodOn(ItemsController.class).delete(entity.getId())).withRel("delete"),
                linkTo(methodOn(ItemsController.class).all()).withRel("all")
        );
    }
    private EntityModel<ItemRepresentationModel> getEntityModel(ItemRepresentationModel model) throws RecordNotFoundException {
        return EntityModel.of(
                model,
                linkTo(methodOn(ItemsController.class).one(model.getId())).withSelfRel(),
                linkTo(methodOn(ItemsController.class).all()).withRel("create"),
                linkTo(methodOn(ItemsController.class).update(model.getId(), null)).withRel("update"),
                linkTo(methodOn(ItemsController.class).updateImage(model.getId(), null)).withRel("updateImage"),
                linkTo(methodOn(ItemsController.class).delete(model.getId())).withRel("delete"),
                linkTo(methodOn(ItemsController.class).all()).withRel("all")
        );
    }
    private ItemRepresentationModel getItemViewModel(Item entity){
        return new ItemModelBuilder()
                .setItemEntity(entity)
                .setFilePathToUrlConverter(filePathToUrlConvertor)
                .setBrand(entity.getBrand())
                .build();
    }
}
