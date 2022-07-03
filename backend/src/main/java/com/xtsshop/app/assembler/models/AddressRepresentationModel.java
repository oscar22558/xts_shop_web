package com.xtsshop.app.assembler.models;

import com.xtsshop.app.db.entities.Address;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AddressRepresentationModel implements AbstractRepresentationModel {
    private String country;
    private String city;
    private String row1;
    private String row2;
    private String row3;
    public static AddressRepresentationModel from(Address entity){
        AddressRepresentationModel model = new AddressRepresentationModel();
        model.country = entity.getCountry();
        model.city = entity.getCity();
        model.row1 = entity.getRow1();
        model.row2 = entity.getRow2();
        model.row3 = entity.getRow3();
        return model;
    }
}
