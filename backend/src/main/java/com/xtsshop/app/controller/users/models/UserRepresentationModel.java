package com.xtsshop.app.controller.users.models;

import com.xtsshop.app.AbstractRepresentationModel;
import com.xtsshop.app.db.entities.AppUser;
import lombok.Getter;

@Getter
public class UserRepresentationModel implements AbstractRepresentationModel {
    private Long id;
    private String username;
    private String email;
    private String phone;

    public UserRepresentationModel(AppUser entity){
        this.id = entity.getId();
        this.username = entity.getUsername();
        this.email = entity.getEmail();
        this.phone = entity.getPhone();
    }
}
