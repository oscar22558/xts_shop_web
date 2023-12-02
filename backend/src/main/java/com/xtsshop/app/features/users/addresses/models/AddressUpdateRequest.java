package com.xtsshop.app.features.users.addresses.models;

import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

@Getter
@Setter
public class AddressUpdateRequest {
    private long id;
    private String country;
    private String city;
    private String district;
    private String area;
    private String row1;
    private Optional<String> row2;
}
