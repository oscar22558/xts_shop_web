package com.xtsshop.app.form.user;

import com.xtsshop.app.db.entities.AppUser;
import com.xtsshop.app.form.Form;
import com.xtsshop.app.request.Request;
import com.xtsshop.app.request.UserRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class UserUpdatePasswordForm {
    @NotBlank
    private String oldPassword;
    @NotBlank
    @Size(min = 8)
    private String password;

}
