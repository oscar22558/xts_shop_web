package com.xtsshop.app.db.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "invoices")
public class Invoice extends AppEntity {

    @Column(name = "items_total", nullable = false)
    private float itemsTotal;

    @Column(name = "shipping_fee", nullable = false)
    private float shippingFee;

    @Column(nullable = false)
    private float total;

    @OneToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;
}
