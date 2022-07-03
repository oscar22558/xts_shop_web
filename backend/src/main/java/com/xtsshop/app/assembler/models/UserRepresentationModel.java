package com.xtsshop.app.assembler.models;

import com.xtsshop.app.db.entities.AppUser;
import lombok.Getter;

@Getter
public class UserRepresentationModel implements AbstractRepresentationModel {
    private Long id;
    private String username;
    private String email;
    private String phone;

    public UserRepresentationModel(Long id, String username, String email, String phone) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.phone = phone;
    }
    public static UserRepresentationModel from(AppUser entity){
        return new UserRepresentationModel(
                entity.getId(),
                entity.getUsername(),
                entity.getEmail(),
                entity.getPhone()
        );
    }
}
