package com.xtsshop.app.controller.users.addresses;

import com.xtsshop.app.advices.exception.RecordNotFoundException;
import com.xtsshop.app.db.entities.Address;
import com.xtsshop.app.db.repositories.AddressJpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public class UserAddressRepo {

    private AddressJpaRepository addressJpaRepository;

    public UserAddressRepo(AddressJpaRepository addressJpaRepository) {
        this.addressJpaRepository = addressJpaRepository;
    }

    public Address findAddressById(long addressId){
        return addressJpaRepository
                .findById(addressId)
                .orElseThrow(()->new RecordNotFoundException("Address " + addressId + " not found."));
    }
}
