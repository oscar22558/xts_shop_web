package com.xtsshop.app.controller.users.addresses.models;

import com.xtsshop.app.db.entities.Address;
import com.xtsshop.app.db.entities.AppUser;
import com.xtsshop.app.helpers.DateTimeHelper;

import java.sql.Date;

public class AddressCreateRequestConvertor {
    private AddressCreateRequest request;
    private AppUser user;

    public AddressCreateRequestConvertor(AddressCreateRequest request, AppUser user) {
        this.request = request;
        this.user = user;
    }

    public Address toEntity(){
        Date now = new DateTimeHelper().now();
        Address address = new Address();
        address.setCreatedAt(now);
        address.setUpdatedAt(now);
        address.setCountry(request.getCountry());
        address.setCity(request.getCity());
        address.setRow1(request.getRow1());
        address.setRow2(request.getRow2());
        address.setDistrict(request.getDistrict());
        address.setArea(request.getArea());
        address.setUser(user);
        return address;
    }
}
