package com.xtsshop.app.form.addresses;

import com.xtsshop.app.request.users.addresses.AddressCreateRequest;
import org.checkerframework.checker.units.qual.A;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;

public class AddressCreateForm {
    @NotNull
    private String country;
    @NotNull
    private String city;
    @NotNull
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
