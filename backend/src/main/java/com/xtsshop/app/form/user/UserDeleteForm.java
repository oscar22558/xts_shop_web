package com.xtsshop.app.form.user;

import com.xtsshop.app.db.entities.AppUser;
import com.xtsshop.app.form.Form;
import com.xtsshop.app.request.UserRequest;

import javax.annotation.Nullable;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

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
