package com.xtsshop.app.db.entities.payment;

import com.xtsshop.app.db.entities.AppEntity;
import com.xtsshop.app.db.entities.Order;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.sql.Date;

@Setter
@Getter
@Entity
@Table(name = "payments")
public class Payment extends AppEntity {
    @Column(nullable = false, name = "paid_total")
    @Min(0)
    private Float paidTotal;

    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;

    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;
}
