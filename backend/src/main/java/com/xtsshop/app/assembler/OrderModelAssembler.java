package com.xtsshop.app.assembler;

import com.xtsshop.app.advice.exception.RecordNotFoundException;
import com.xtsshop.app.advice.exception.UnAuthorizationException;
import com.xtsshop.app.controller.OrderController;
import com.xtsshop.app.db.entities.Order;
import com.xtsshop.app.service.storage.StorageService;
import com.xtsshop.app.viewmodel.OrderModel;
import com.xtsshop.app.viewmodel.builder.OrderModelBuilder;
import lombok.SneakyThrows;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class OrderModelAssembler extends AbstractModelAssembler<OrderModel, Order> {
    private StorageService storageService;

    @SneakyThrows
    @Override
    public EntityModel<OrderModel> toModel(Order entity) {
        return EntityModel.of(
                new OrderModelBuilder().setStorageService(storageService).setEntity(entity).build(),
                linkTo(methodOn(OrderController.class).get(entity.getId())).withSelfRel()
        );
    }
    public OrderModelAssembler setStorageService(StorageService storageService){
        this.storageService = storageService;
        return this;
    }
}
