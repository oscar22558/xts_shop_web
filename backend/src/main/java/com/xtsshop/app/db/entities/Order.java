package com.xtsshop.app.db.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "orders")
public class Order extends AppEntity {
    @Column(name = "recipient_name")
    private String recipientName;

    @Column(name = "recipient_email")
    private String recipientEmail;

    @Column(name = "recipient_phone")
    private String recipientPhone;

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
    private ShippingAddress shippingAddress;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private AppUser user;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderedItem> orderedItems;

    @Column(name = "payment_intent_id", nullable = false)
    private String paymentIntentId;

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
    private Invoice invoice;

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
