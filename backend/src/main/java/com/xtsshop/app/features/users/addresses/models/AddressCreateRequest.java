package com.xtsshop.app.features.users.addresses.models;

import lombok.Getter;
import lombok.Setter;

import javax.annotation.Nullable;

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
