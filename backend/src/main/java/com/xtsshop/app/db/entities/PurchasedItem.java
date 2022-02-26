package com.xtsshop.app.db.entities;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "purchased_items")
public class PurchasedItem {
    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false, name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;

    @ManyToOne
    @JoinColumn(nullable = false, name = "item_id")
    private Item item;

    @ManyToOne
    @JoinColumn(nullable = false, name = "order_id")
    private Order order;
}
