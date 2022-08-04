package com.xtsshop.app.controller.users.addresses.models;

import lombok.Getter;
import lombok.Setter;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class AddressCreateRequest {
    private String country;
    private String city;
    private String district;
    private String area;
    private String row1;
    @Nullable
    private String row2;
}
