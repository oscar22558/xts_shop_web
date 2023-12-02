package com.xtsshop.app.features.users.addresses;

import com.xtsshop.app.features.users.addresses.models.AddressCreateForm;
import com.xtsshop.app.features.users.addresses.models.AddressUpdateForm;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class UserAddressesController {

    private UserAddressesService service;

    public UserAddressesController(UserAddressesService service) {
        this.service = service;
    }

    @PostMapping("/api/users/address")
    public ResponseEntity<?> addAddress(@RequestBody @Valid AddressCreateForm form){
        service.addAddress(form.toRequest());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/api/users/address/{addressId}")
    public ResponseEntity<?> editAddress(@PathVariable long addressId, @RequestBody @Valid AddressUpdateForm form){
        service.updateAddress(form.toRequest(addressId));
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/api/users/address/{addressId}")
    public ResponseEntity<?> deleteAddress(@PathVariable long addressId){
        service.deleteAddress(addressId);
        return ResponseEntity.ok().build();
    }
}
