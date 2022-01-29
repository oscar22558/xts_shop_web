package com.xtsshop.app.entities.payment;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "credit_card")
@DiscriminatorValue("CreditCard")
public class CreditCard extends Payment{

    @Column(nullable = false, name = "card_num",length = 100)
    private String cardNum;
    @Column(nullable = false, name = "holderName",length = 100)
    private String holderName;

    @Column(nullable = false, name = "bankCode",length = 100)
    private String bankCode;
    @Column(nullable = false, name = "valid_until")
    private Date validUntil;
}
