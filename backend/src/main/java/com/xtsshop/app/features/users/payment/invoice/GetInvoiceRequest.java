package com.xtsshop.app.features.users.payment.invoice;

import com.xtsshop.app.features.users.payment.models.ItemQuantity;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
public class GetInvoiceRequest {
    @Size(min = 1)
    @NotNull
    private List<@NotNull ItemQuantity> itemQuantities;
}
