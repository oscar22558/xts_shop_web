package com.xtsshop.app.features.users.payment.models;

import com.xtsshop.app.db.entities.Invoice;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UpdateOrderRequest extends Address {
    private List<ItemQuantity> itemQuantities;
    private String paymentIntentId;
    private Invoice invoice;
}
