package com.xtsshop.app.domain.request.users.addresses;

import lombok.Getter;
import lombok.Setter;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class AddressCreateRequest {
    private String country;
    private String city;
    private String row1;
    @Nullable
    private String row2;
    @Nullable
    private String row3;
}
