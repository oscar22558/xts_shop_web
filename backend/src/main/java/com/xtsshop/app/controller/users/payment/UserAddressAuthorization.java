package com.xtsshop.app.controller.users.payment;

import com.xtsshop.app.advices.exception.UnAuthorizationException;
import com.xtsshop.app.db.entities.Address;
import com.xtsshop.app.db.entities.AppUser;

public class UserAddressAuthorization {
    private AppUser user;
    private Address address;

    public UserAddressAuthorization(AppUser user, Address address) {
        this.user = user;
        this.address = address;
    }

    public void checkPermission(){
        if(user.getId() != address.getUser().getId()){
           throw new UnAuthorizationException();
        }
    }
}
