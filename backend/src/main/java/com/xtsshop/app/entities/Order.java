package com.xtsshop.app.entities;

import com.xtsshop.app.entities.payment.CreditCard;
import com.xtsshop.app.entities.payment.EPS;
import com.xtsshop.app.entities.payment.Payment;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Any;
import org.hibernate.annotations.AnyMetaDef;
import org.hibernate.annotations.MetaValue;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false, name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;

    @Column(nullable = false)
    private double total;

    @Column(nullable = false, name = "discounted_total")
    private double discountedTotal;

    @ManyToOne
    @JoinColumn(nullable = false, name = "shipping_address")
    private Address shippingAddress;

    @OneToOne(mappedBy = "order")
    private Payment payment;

    @ManyToOne
    @JoinColumn(nullable = false, name = "user_id")
    private User user;

    @OneToMany(mappedBy = "order")
    private List<Coupon> coupon;

    @OneToMany(mappedBy = "order")
    private List<PurchasedItem> purchasedItems;
}
