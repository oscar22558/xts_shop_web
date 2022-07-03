package com.xtsshop.app.assembler.models;

import com.xtsshop.app.assembler.ItemModelAssembler;
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