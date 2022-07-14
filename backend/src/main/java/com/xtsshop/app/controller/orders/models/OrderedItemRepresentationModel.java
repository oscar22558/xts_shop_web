package com.xtsshop.app.controller.orders.models;

import com.xtsshop.app.AbstractRepresentationModel;
import com.xtsshop.app.controller.items.models.ItemRepresentationModel;
import com.xtsshop.app.controller.items.models.ItemModelAssembler;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.EntityModel;

@Getter
@Setter
@NoArgsConstructor
public class OrderedItemRepresentationModel implements AbstractRepresentationModel {
    private EntityModel<ItemRepresentationModel> item;
    private Integer quantity;

    public OrderedItemRepresentationModel(ItemRepresentationModel item, Integer quantity) {
        ItemModelAssembler itemModelAssembler = new ItemModelAssembler();
        this.item = itemModelAssembler.toEntityModel(item);
        this.quantity = quantity;
    }
}