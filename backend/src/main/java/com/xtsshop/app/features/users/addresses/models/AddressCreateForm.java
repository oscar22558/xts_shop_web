package com.xtsshop.app.features.users.addresses.models;

import lombok.Getter;
import lombok.Setter;

import javax.annotation.Nullable;
import javax.validation.constraints.NotBlank;

@Setter
@Getter
public class AddressCreateForm {
    @NotBlank
    private String country;
    @NotBlank
    private String city;
    @NotBlank
    private String district;
    @NotBlank
    private String area;
    @NotBlank
    private String row1;
    @Nullable
    private String row2;
    public AddressCreateRequest toRequest(){
        AddressCreateRequest request = new AddressCreateRequest();
        request.setCity(city);
        request.setCountry(country);
        request.setRow1(row1);
        request.setRow2(row2);
        request.setDistrict(district);
        request.setArea(area);
        return request;
    }
}
