package com.xtsshop.app.viewmodel;

import com.xtsshop.app.assembler.ItemModelAssembler;
import com.xtsshop.app.db.entities.Item;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.EntityMode;
import org.springframework.hateoas.EntityModel;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
public class OrderedItemModel implements AbstractViewModel {
    private EntityModel<ItemModel> item;
    private Integer quantity;

    public OrderedItemModel(ItemModel item, Integer quantity) {
        ItemModelAssembler itemModelAssembler = new ItemModelAssembler();
        this.item = itemModelAssembler.toEntityModel(item);
        this.quantity = quantity;
    }
}