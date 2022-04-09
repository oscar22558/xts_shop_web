package com.xtsshop.app.db.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "coupons")
public class Coupon extends AppEntity{
    @Column(nullable = false, length = 100)
    private String code;

    @Column(nullable = false, name = "valid_until")
    private Date validUntil;

    @Column(name = "discount_percentage")
    private Date discountPercentage;

    @Column(name = "discount")
    private Date discount;

    @Column(nullable = false, name = "used")
    private boolean used;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private AppUser user;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    public Coupon(Date createdAt, Date updatedAt, String code, Date validUntil, Date discountPercentage, Date discount, boolean used, AppUser user, Order order) {
        super(createdAt, updatedAt);
        this.code = code;
        this.validUntil = validUntil;
        this.discountPercentage = discountPercentage;
        this.discount = discount;
        this.used = used;
        this.user = user;
        this.order = order;
    }
}
