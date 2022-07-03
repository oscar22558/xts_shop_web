package com.xtsshop.app.domain.service.brands;

import com.xtsshop.app.db.entities.Brand;
import com.xtsshop.app.domain.request.brands.BrandCreateRequest;
import com.xtsshop.app.util.DateTimeUtil;
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
        Date now = new DateTimeUtil().now();
        Brand brand = new Brand();
        brand.setName(request.getName());
        brand.setCreatedAt(now);
        brand.setUpdatedAt(now);
        return brand;
    }
}
