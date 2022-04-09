package com.xtsshop.app.db.entities;

import com.xtsshop.app.db.entities.payment.Payment;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;


public enum OrderStatus{
    WAITING_PAYMENT, PAID, PROCESSING, SHIPPING, SHIPPED, CANCELING, CANCELED
}
