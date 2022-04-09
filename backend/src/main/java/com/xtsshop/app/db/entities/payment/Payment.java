package com.xtsshop.app.db.entities.payment;

import com.xtsshop.app.db.entities.AppEntity;
import com.xtsshop.app.db.entities.Order;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

@Setter
@Getter
@Entity
@Table(name = "payments")
public class Payment extends AppEntity {
    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;

    @Column(nullable = false, name = "card_num", length = 100)
    private String cardNum;

    @Column(nullable = false, name = "holderName", length = 100)
    private String holderName;

    @Column(nullable = false, name = "bankCode", length = 100)
    private String bankCode;

    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;
}
