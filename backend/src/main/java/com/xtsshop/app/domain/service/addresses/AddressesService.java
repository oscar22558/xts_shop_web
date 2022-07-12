package com.xtsshop.app.domain.service.addresses;

import com.xtsshop.app.advice.exception.RecordNotFoundException;
import com.xtsshop.app.db.entities.Address;
import com.xtsshop.app.db.repositories.AddressRepository;
import org.springframework.stereotype.Service;

@Service
public class AddressesService {
    private AddressRepository addressRepository;

    public AddressesService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public Address get(Long id) throws RecordNotFoundException{
        return addressRepository.findById(id).orElseThrow(()->new RecordNotFoundException("Address with id "+id+" not found."));
    }
}