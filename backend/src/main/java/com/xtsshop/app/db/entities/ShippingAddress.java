package com.xtsshop.app.db.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "shipping_addresses")
public class ShippingAddress  extends AppEntity{
    @Column(nullable = false, length = 100)
    private String country;

    @Column(nullable = false, length = 100)
    private String city;

    @Column(length = 100)
    private String district;

    @Column(length = 100)
    private String area;

    @Column(nullable = false, length = 100)
    private String row1;

    @Column(length = 100)
    private String row2;

    @OneToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;
}
