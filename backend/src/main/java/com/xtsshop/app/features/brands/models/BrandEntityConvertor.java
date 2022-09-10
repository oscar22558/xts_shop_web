package com.xtsshop.app.features.brands.models;

import com.xtsshop.app.db.entities.Brand;
import com.xtsshop.app.helpers.DateTimeHelper;
import org.springframework.stereotype.Component;
import java.sql.Date;

@Component
public class BrandEntityConvertor {
    private BrandCreateRequest request;

    public BrandEntityConvertor setRequest(BrandCreateRequest request) {
        this.request = request;
        return this;
    }

    public Brand getEntity(){
        Date now = new DateTimeHelper().now();
        Brand brand = new Brand();
        brand.setName(request.getName());
        brand.setCreatedAt(now);
        brand.setUpdatedAt(now);
        return brand;
    }
}
