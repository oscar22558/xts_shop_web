package com.xtsshop.app.form.user;

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
