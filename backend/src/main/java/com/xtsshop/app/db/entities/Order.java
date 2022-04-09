package com.xtsshop.app.db.entities;

import com.xtsshop.app.db.entities.payment.Payment;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

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

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @ManyToMany
    @JoinTable(
            name = "orders_price_histories",
            joinColumns = @JoinColumn(
                    name = "order_id", referencedColumnName = "id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "price_history_id", referencedColumnName = "id"
            )
    )
    private Set<PriceHistory> priceHistories;

    public void addPriceHistory(PriceHistory priceHistory){
        this.priceHistories.add(priceHistory);
        priceHistory.getOrders().add(this);
    }
    public void removePriceHistory(PriceHistory priceHistory){
        this.priceHistories.remove(priceHistory);
        priceHistory.getOrders().remove(this);
    }
}
