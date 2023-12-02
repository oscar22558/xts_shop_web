package com.xtsshop.app.features.orders.entitybuilders;

import com.xtsshop.app.db.entities.Address;
import com.xtsshop.app.db.entities.ShippingAddress;
import com.xtsshop.app.helpers.DateTimeHelper;

import java.sql.Date;

public class ShippingAddressEntityBuilder {
    private Address address;

    public ShippingAddressEntityBuilder(Address address) {
        this.address = address;
    }

    public ShippingAddress build(){
        Date now = new DateTimeHelper().now();
       ShippingAddress shippingAddress = new ShippingAddress();
       shippingAddress.setCreatedAt(now);
       shippingAddress.setUpdatedAt(now);
       shippingAddress.setCountry(address.getCountry());
       shippingAddress.setCity(address.getCity());
       shippingAddress.setArea(address.getArea());
       shippingAddress.setDistrict(address.getDistrict());
       shippingAddress.setRow2(address.getRow2());
       shippingAddress.setRow1(address.getRow1());
       return shippingAddress;
    }
}
