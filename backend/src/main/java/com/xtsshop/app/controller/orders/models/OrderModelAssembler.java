package com.xtsshop.app.controller.orders.models;

import com.xtsshop.app.AbstractModelAssembler;
import com.xtsshop.app.controller.orders.OrderController;
import com.xtsshop.app.db.entities.Order;
import com.xtsshop.app.controller.storage.FilePathToUrlConverter;
import lombok.SneakyThrows;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class OrderModelAssembler extends AbstractModelAssembler<OrderRepresentationModel, Order> {

    @SneakyThrows
    @Override
    public EntityModel<OrderRepresentationModel> toModel(Order entity) {
        return EntityModel.of(
                new OrderRepresentationModel(entity),
                WebMvcLinkBuilder.linkTo(methodOn(OrderController.class).get(entity.getId())).withSelfRel()
        );
    }
}
