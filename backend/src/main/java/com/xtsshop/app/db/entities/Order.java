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

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderedItem> orderedItems;

    public void addOrderItems(OrderedItem orderedItem){
        orderedItem.setOrder(this);
        if(orderedItems != null)
            orderedItems.add(orderedItem);
    }
    public void removeOrderItems(Long orderItemId){
        if(orderedItems != null){
            orderedItems.stream().filter(item->item.getId() == orderItemId).findAny().ifPresent(item->{
                item.setOrder(null);
                orderedItems.remove(item);
            });
        }
    }
}
