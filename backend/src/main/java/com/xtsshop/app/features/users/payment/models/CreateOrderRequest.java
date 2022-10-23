package com.xtsshop.app.features.users.payment.models;

import com.xtsshop.app.db.entities.Invoice;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CreateOrderRequest extends Address {
    private String recipientFirstName;
    private String recipientLastName;
    private String recipientEmail;
    private String recipientPhone;
    private List<ItemQuantity> itemQuantities;
    private String paymentIntentId;
    private Invoice invoice;
}