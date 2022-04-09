package com.xtsshop.app.request;

import com.xtsshop.app.db.entities.AppUser;

import javax.annotation.Nullable;

public class UserRequest {
    private String username;
    private String password;
    private String email;
    @Nullable
    private String phone;

    public UserRequest(String username, String password, String email, @Nullable String phone) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
    }

    public AppUser toEntity(){
        return new AppUser(username, password, email, phone);
    }
}
