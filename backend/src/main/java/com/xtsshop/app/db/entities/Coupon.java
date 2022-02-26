package com.xtsshop.app.db.entities;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "coupons")
public class Coupon {

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false, name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;

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
    private User user;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    public Coupon(){}
    public Coupon(long id, Date createdAt, Date updatedAt, String code, Date validUntil, Date discountPercentage, Date discount, boolean used, User user, Order order) {
        this.id = id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.code = code;
        this.validUntil = validUntil;
        this.discountPercentage = discountPercentage;
        this.discount = discount;
        this.used = used;
        this.user = user;
        this.order = order;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getValidUntil() {
        return validUntil;
    }

    public void setValidUntil(Date validUntil) {
        this.validUntil = validUntil;
    }

    public Date getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(Date discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public Date getDiscount() {
        return discount;
    }

    public void setDiscount(Date discount) {
        this.discount = discount;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
