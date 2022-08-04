package com.xtsshop.app.controller.orders;

import com.xtsshop.app.advices.exception.RecordNotFoundException;
import com.xtsshop.app.db.entities.Address;
import com.xtsshop.app.db.repositories.AddressJpaRepository;
import org.springframework.stereotype.Service;

@Service
public class AddressesService {
    private AddressJpaRepository addressJpaRepository;

    public AddressesService(AddressJpaRepository addressJpaRepository) {
        this.addressJpaRepository = addressJpaRepository;
    }

    public Address get(Long id) throws RecordNotFoundException{
        return addressJpaRepository.findById(id).orElseThrow(()->new RecordNotFoundException("Address with id "+id+" not found."));
    }
}
