package com.xtsshop.app.db.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.sql.Date;

@Setter
@Getter
@NoArgsConstructor
@Entity(name = "ordered_items")
public class OrderedItem extends AppEntity {
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Order order;
    @ManyToOne
    @JoinColumn(name = "item_id", referencedColumnName = "id")
    private Item item;
    @ManyToOne
    @JoinColumn(name = "price_history_id", referencedColumnName = "id")
    private PriceHistory orderPrice;

    public OrderedItem(Date createAt, Date updated, Item item, int quantity){
        super(createAt, updated);
        this.quantity = quantity;
        this.item = item;
    }
}
