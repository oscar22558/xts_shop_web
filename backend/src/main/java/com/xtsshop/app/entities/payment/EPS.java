package com.xtsshop.app.entities.payment;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "eps")
@DiscriminatorValue("EPS")
public class EPS implements Payment {

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false, name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;
    @Column(nullable = false, name = "card_num",length = 100)
    private String cardNum;
    @Column(nullable = false, name = "holderName",length = 100)
    private String holderName;

    @Column(nullable = false, name = "bankCode",length = 100)
    private String bankCode;
}
