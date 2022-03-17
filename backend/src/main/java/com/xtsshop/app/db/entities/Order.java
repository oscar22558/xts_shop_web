package com.xtsshop.app.db.entities;

import com.xtsshop.app.db.entities.payment.Payment;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order extends AppEntity {

    @ManyToOne
    @JoinColumn(nullable = false, name = "shipping_address")
    private Address shippingAddress;

    @OneToOne(mappedBy = "order")
    private Payment payment;

    @ManyToOne
    @JoinColumn(nullable = false, name = "user_id")
    private AppUser user;

    @OneToMany(mappedBy = "order")
    private List<Coupon> coupon;

    @OneToMany(mappedBy = "order")
    private List<PurchasedItem> purchasedItems;
}
