package com.xtsshop.app.assembler;

import com.xtsshop.app.db.entities.Order;
import com.xtsshop.app.viewmodel.OrderViewModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Component;

@Component
public class OrderModelAssembler extends AbstractModelAssembler<OrderViewModel, Order> {
    @Override
    public EntityModel<OrderViewModel> toModel(Order entity) {
        return EntityModel.of(
                OrderViewModel.from(entity)
                );
    }
}
