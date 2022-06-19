package com.xtsshop.app.assembler;

import com.xtsshop.app.controller.OrderController;
import com.xtsshop.app.db.entities.Order;
import com.xtsshop.app.domain.service.storage.FilePathToUrlConverter;
import com.xtsshop.app.domain.service.storage.StorageService;
import com.xtsshop.app.viewmodel.OrderModel;
import com.xtsshop.app.viewmodel.builder.OrderModelBuilder;
import lombok.SneakyThrows;
import org.h2.store.fs.FilePath;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Component;

import java.io.File;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class OrderModelAssembler extends AbstractModelAssembler<OrderModel, Order> {
    private FilePathToUrlConverter filePathToUrlConverter;

    @SneakyThrows
    @Override
    public EntityModel<OrderModel> toModel(Order entity) {
        return EntityModel.of(
                new OrderModelBuilder().setFilePathToUrlConverter(filePathToUrlConverter).setEntity(entity).build(),
                linkTo(methodOn(OrderController.class).get(entity.getId())).withSelfRel()
        );
    }
    public OrderModelAssembler setFilePathToUrlConverter(FilePathToUrlConverter filePathToUrlConverter){
        this.filePathToUrlConverter = filePathToUrlConverter;
        return this;
    }
}
