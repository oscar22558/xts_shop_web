package com.xtsshop.app.controller.brands.models;

import com.xtsshop.app.AbstractRepresentationModel;
import com.xtsshop.app.db.entities.Brand;

public class BrandRepresentationModel implements AbstractRepresentationModel {
    private Brand brand;

    public BrandRepresentationModel(Brand brand) {
        this.brand = brand;
    }

    public Long getId(){
        return brand.getId();
    }
    public String getName(){
        return brand.getName();
    }

}
