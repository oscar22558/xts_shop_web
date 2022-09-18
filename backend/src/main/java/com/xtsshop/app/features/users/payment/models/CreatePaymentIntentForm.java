package com.xtsshop.app.features.users.payment.models;

import com.xtsshop.app.validations.groups.ColumnEmpty;
import com.xtsshop.app.validations.groups.ColumnLength;
import com.xtsshop.app.validations.groups.ColumnPattern;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;
import java.util.List;

@Getter
@Setter
public class CreatePaymentIntentForm {
    @NotBlank(groups = ColumnEmpty.class)
    @Size(max = 100, groups = ColumnLength.class)
    private String recipientFirstName;

    @NotBlank(groups = ColumnEmpty.class)
    @Size(max = 100, groups = ColumnLength.class)
    private String recipientLastName;

    @NotBlank(groups = ColumnEmpty.class)
    @Email(groups = ColumnPattern.class)
    private String recipientEmail;

    @NotBlank(groups = ColumnEmpty.class)
    @Size(max = 15, groups = ColumnLength.class)
    private String recipientPhone;

    @NotNull(groups = ColumnEmpty.class)
    @Size(min = 1, groups = ColumnLength.class)
    private List<@NotNull ItemQuantity> itemQuantities;

    @NotNull(groups = ColumnEmpty.class)
    private long userAddressId;

}
