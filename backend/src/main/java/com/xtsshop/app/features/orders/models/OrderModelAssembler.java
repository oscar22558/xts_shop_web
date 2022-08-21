package com.xtsshop.app.features.orders.models;

import com.xtsshop.app.AbstractModelAssembler;
import com.xtsshop.app.features.orders.OrderController;
import com.xtsshop.app.db.entities.Order;
import com.xtsshop.app.features.storage.FilePathToUrlConverter;
import lombok.SneakyThrows;
import org.apache.tomcat.jni.File;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class OrderModelAssembler extends AbstractModelAssembler<OrderRepresentationModel, Order> {
    private FilePathToUrlConverter filePathConverter;

    public OrderModelAssembler(FilePathToUrlConverter filePathConverter) {
        this.filePathConverter = filePathConverter;
    }

    @SneakyThrows
    @Override
    public EntityModel<OrderRepresentationModel> toModel(Order entity) {
        return EntityModel.of(
                new OrderRepresentationModel(entity, filePathConverter),
                WebMvcLinkBuilder.linkTo(methodOn(OrderController.class).get(entity.getId())).withSelfRel()
        );
    }
}
