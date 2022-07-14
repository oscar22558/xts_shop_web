package com.xtsshop.app.controller.users.models;

import com.xtsshop.app.db.entities.AppUser;

import javax.validation.constraints.NotBlank;

public class UserDeleteForm implements Form<UserRequest, AppUser> {
    @NotBlank
    private String username;

    @Override
    public UserRequest toRequest() {
        UserRequest request = new UserRequest();
        request.setUsername(username);
        return request;
    }
}
