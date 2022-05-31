package com.xtsshop.app.db.entities.builder;

import com.xtsshop.app.db.entities.Brand;
import com.xtsshop.app.util.DateTimeUtil;

import java.sql.Date;

public class BrandBuilder {
    private String name;

    public BrandBuilder setName(String name) {
        this.name = name;
        return this;
    }
    public Brand build(){
        Date now = new DateTimeUtil().now();
        Brand brand = new Brand();
        brand.setName(name);
        brand.setCreatedAt(now);
        brand.setUpdatedAt(now);
        return brand;
    }
}
