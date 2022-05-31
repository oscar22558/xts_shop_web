package com.xtsshop.app.form.user;

import com.xtsshop.app.db.entities.AppUser;
import com.xtsshop.app.form.Form;
import com.xtsshop.app.domain.request.UserRequest;

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
