package com.xtsshop.app.db.entities.payment;

import com.xtsshop.app.db.entities.Order;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Inheritance
@Table(name = "payment")
public class Payment {
    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false, name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;
    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;
}
