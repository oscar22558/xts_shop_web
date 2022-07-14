package com.xtsshop.app.controller.users.addresses;

import com.xtsshop.app.controller.users.addresses.models.AddressCreateRequest;
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
    private String row1;
    @Nullable
    private String row2;
    @Nullable
    private String row3;
    public AddressCreateRequest toRequest(){
        AddressCreateRequest request = new AddressCreateRequest();
        request.setCity(city);
        request.setCountry(country);
        request.setRow1(row1);
        request.setRow2(row2);
        request.setRow3(row3);
        return request;
    }
}
