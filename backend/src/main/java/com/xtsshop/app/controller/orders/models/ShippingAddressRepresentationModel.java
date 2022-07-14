package com.xtsshop.app.controller.orders.models;

import com.xtsshop.app.AbstractRepresentationModel;
import com.xtsshop.app.db.entities.Address;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ShippingAddressRepresentationModel implements AbstractRepresentationModel {
    private String country;
    private String city;
    private String row1;
    private String row2;
    private String row3;

    public ShippingAddressRepresentationModel(Address entity){
        this.country = entity.getCountry();
        this.city = entity.getCity();
        this.row1 = entity.getRow1();
        this.row2 = entity.getRow2();
        this.row3 = entity.getRow3();
    }
}
