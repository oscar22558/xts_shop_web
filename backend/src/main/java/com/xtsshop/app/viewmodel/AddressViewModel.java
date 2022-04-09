package com.xtsshop.app.viewmodel;

import com.xtsshop.app.db.entities.Address;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Getter
@NoArgsConstructor
public class AddressViewModel implements AbstractViewModel{
    private String country;
    private String city;
    private String row1;
    private String row2;
    private String row3;
    public static AddressViewModel from(Address entity){
        AddressViewModel model = new AddressViewModel();
        model.country = entity.getCountry();
        model.city = entity.getCity();
        model.row1 = entity.getRow1();
        model.row2 = entity.getRow2();
        model.row3 = entity.getRow3();
        return model;
    }
}
