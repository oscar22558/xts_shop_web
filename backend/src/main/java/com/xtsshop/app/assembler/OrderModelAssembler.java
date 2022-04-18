package com.xtsshop.app.assembler;

import com.xtsshop.app.db.entities.Order;
import com.xtsshop.app.viewmodel.OrderModel;
import com.xtsshop.app.viewmodel.builder.OrderModelBuilder;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Component;

@Component
public class OrderModelAssembler extends AbstractModelAssembler<OrderModel, Order> {
    @Override
    public EntityModel<OrderModel> toModel(Order entity) {
        return EntityModel.of(
                new OrderModelBuilder().setEntity(entity).build()
                );
    }
}
