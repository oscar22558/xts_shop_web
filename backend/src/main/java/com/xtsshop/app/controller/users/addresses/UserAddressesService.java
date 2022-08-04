package com.xtsshop.app.controller.users.addresses;

import com.xtsshop.app.controller.authentication.UserIdentityService;
import com.xtsshop.app.controller.users.addresses.models.AddressCreateRequest;
import com.xtsshop.app.controller.users.addresses.models.AddressCreateRequestConvertor;
import com.xtsshop.app.controller.users.addresses.models.AddressUpdateRequest;
import com.xtsshop.app.controller.users.addresses.models.AddressUpdateRequestConvertor;
import com.xtsshop.app.db.entities.Address;
import com.xtsshop.app.db.entities.AppUser;
import com.xtsshop.app.db.repositories.AddressJpaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UserAddressesService {
    private UserIdentityService userIdentityService;
    private UserAddressRepo userAddressRepo;
    private AddressJpaRepository addressJpaRepository;
    private AppUser user;

    public UserAddressesService(UserIdentityService userIdentityService, UserAddressRepo userAddressRepo, AddressJpaRepository addressJpaRepository) {
        this.userIdentityService = userIdentityService;
        this.userAddressRepo = userAddressRepo;
        this.addressJpaRepository = addressJpaRepository;
    }

    public void addAddress(AddressCreateRequest request){
        user = userIdentityService.getUser();
        Address address =
                new AddressCreateRequestConvertor(request, user).toEntity();
        addressJpaRepository.save(address);
    }

    public void updateAddress(AddressUpdateRequest request){
        user = userIdentityService.getUser();
        Address address = userAddressRepo.findAddressById(request.getId());
        new AddressAuthorization(address, user).authorize();
        Address updatedAddress =
                new AddressUpdateRequestConvertor(request, user).toEntity();
        updatedAddress = addressJpaRepository.save(updatedAddress);
        Logger logger = LoggerFactory.getLogger(UserAddressesService.class);
        logger.info("change address row 1: " + updatedAddress.getRow1());
    }

    public void deleteAddress(long addressId){
        user = userIdentityService.getUser();
        Address address = userAddressRepo.findAddressById(addressId);
        new AddressAuthorization(address, user).authorize();
        addressJpaRepository.deleteById(addressId);
    }

}
