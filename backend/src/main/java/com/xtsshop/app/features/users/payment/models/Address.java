package com.xtsshop.app.features.users.payment.models;

import com.xtsshop.app.validations.groups.ColumnEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class Address {

    @NotNull(groups = ColumnEmpty.class)
    private long userAddressId;

    @NotNull(groups = ColumnEmpty.class)
    private String country;

    @NotNull(groups = ColumnEmpty.class)
    private String city;

    @NotNull(groups = ColumnEmpty.class)
    private String district;

    @NotNull(groups = ColumnEmpty.class)
    private String area;

    @NotNull(groups = ColumnEmpty.class)
    private String row1;

    private String row2;

}
