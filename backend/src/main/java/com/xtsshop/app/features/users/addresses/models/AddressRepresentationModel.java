package com.xtsshop.app.features.users.addresses.models;

import lombok.Getter;

@Getter
public class AddressRepresentationModel {
    private long id;
    private String country;
    private String city;
    private String district;
    private String area;
    private String row1;
    private String row2;

    public AddressRepresentationModel(long id, String country, String city, String district, String area, String row1, String row2) {
        this.id = id;
        this.country = country;
        this.city = city;
        this.district = district;
        this.area = area;
        this.row1 = row1;
        this.row2 = row2;
    }
}
