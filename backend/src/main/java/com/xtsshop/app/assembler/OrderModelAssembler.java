package com.xtsshop.app.assembler;

import com.xtsshop.app.controller.OrderController;
import com.xtsshop.app.db.entities.Order;
import com.xtsshop.app.domain.service.storage.FilePathToUrlConverter;
import com.xtsshop.app.assembler.models.OrderRepresentationModel;
import com.xtsshop.app.assembler.models.builder.OrderModelBuilder;
import lombok.SneakyThrows;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class OrderModelAssembler extends AbstractModelAssembler<OrderRepresentationModel, Order> {
    private FilePathToUrlConverter filePathToUrlConverter;

    @SneakyThrows
    @Override
    public EntityModel<OrderRepresentationModel> toModel(Order entity) {
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
