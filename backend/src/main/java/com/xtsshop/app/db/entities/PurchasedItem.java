package com.xtsshop.app.db.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "purchased_items")
public class PurchasedItem extends AppEntity {
    @ManyToOne
    @JoinColumn(nullable = false, name = "item_id")
    private Item item;

    @ManyToOne
    @JoinColumn(nullable = false, name = "order_id")
    private Order order;
}
