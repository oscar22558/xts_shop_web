package com.xtsshop.app.viewmodel;

import com.xtsshop.app.db.entities.AppUser;
import lombok.Getter;

@Getter
public class UserViewModel implements AbstractViewModel{
    private Long id;
    private String username;
    private String email;
    private String phone;

    public UserViewModel(Long id, String username, String email, String phone) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.phone = phone;
    }
    public static UserViewModel from(AppUser entity){
        return new UserViewModel(
                entity.getId(),
                entity.getUsername(),
                entity.getEmail(),
                entity.getPhone()
        );
    }
}
