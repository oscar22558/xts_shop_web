package com.xtsshop.app.controller.users.models;

import com.xtsshop.app.AbstractRepresentationModel;
import com.xtsshop.app.controller.users.addresses.models.AddressRepresentationModel;
import com.xtsshop.app.db.entities.AppUser;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class UserRepresentationModel implements AbstractRepresentationModel {
    private Long id;
    private String username;
    private String email;
    private String phone;
    private List<AddressRepresentationModel> addresses;

    public UserRepresentationModel(AppUser entity){
        this.id = entity.getId();
        this.username = entity.getUsername();
        this.email = entity.getEmail();
        this.phone = entity.getPhone();
        this.addresses = entity.getAddresses().stream().map(addressEntity->new AddressRepresentationModel(
                addressEntity.getId(),
                addressEntity.getCountry(),
                addressEntity.getCity(),
                addressEntity.getDistrict(),
                addressEntity.getArea(),
                addressEntity.getRow1(),
                addressEntity.getRow2()
        )).collect(Collectors.toList());
    }
}
