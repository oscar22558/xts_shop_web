package com.xtsshop.app.features.users.payment.models;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
public class CreatePaymentIntentForm {

    @Size(min = 1)
    @NotNull
    private List<@NotNull ItemQuantity> itemQuantities;
    @Min(-1)
    private long userAddressId;

}
