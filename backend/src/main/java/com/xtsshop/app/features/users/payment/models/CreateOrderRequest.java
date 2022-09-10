package com.xtsshop.app.features.users.payment.models;

import com.xtsshop.app.db.entities.Invoice;
import com.xtsshop.app.validations.groups.ColumnEmpty;
import com.xtsshop.app.validations.groups.ColumnLength;
import com.xtsshop.app.validations.groups.ColumnPattern;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
public class CreateOrderRequest {
    private String recipientName;
    private String recipientEmail;
    private String recipientPhone;
    private List<ItemQuantity> itemQuantities;
    private long userAddressId;
    private String paymentIntentId;
    private Invoice invoice;
}