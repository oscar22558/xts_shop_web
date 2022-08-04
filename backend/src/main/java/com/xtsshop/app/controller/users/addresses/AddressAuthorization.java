package com.xtsshop.app.controller.users.addresses;

import com.xtsshop.app.advices.exception.UnAuthorizationException;
import com.xtsshop.app.db.entities.Address;
import com.xtsshop.app.db.entities.AppUser;

public class AddressAuthorization {

    private Address address;
    private AppUser user;

    public AddressAuthorization(Address address, AppUser user) {
        this.address = address;
        this.user = user;
    }

    public void authorize(){
        if(address.getUser().getId() != user.getId()){
            throw new UnAuthorizationException();
        }
    }
}
