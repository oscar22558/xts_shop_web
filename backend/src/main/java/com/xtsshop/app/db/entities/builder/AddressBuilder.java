package com.xtsshop.app.db.entities.builder;

import com.xtsshop.app.db.entities.Address;
import com.xtsshop.app.db.entities.AppUser;

public class AddressBuilder {
    private String country;
    private String city;
    private String row1;
    private String row2;
    private String row3;
    private AppUser user;

    public AddressBuilder setCountry(String country) {
        this.country = country;
        return this;
    }

    public AddressBuilder setCity(String city) {
        this.city = city;
        return this;
    }

    public AddressBuilder setRow1(String row1) {
        this.row1 = row1;
        return this;
    }

    public AddressBuilder setRow2(String row2) {
        this.row2 = row2;
        return this;
    }

    public AddressBuilder setRow3(String row3) {
        this.row3 = row3;
        return this;
    }

    public AddressBuilder setUser(AppUser user) {
        this.user = user;
        return this;
    }
    public Address build(){
        Address address = new Address();
        address.setCountry(country);
        address.setCity(city);
        address.setRow1(row1);
        address.setRow2(row2);
        address.setRow3(row3);
        address.setUser(user);
        return address;
    }
}
