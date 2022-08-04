package com.xtsshop.app.features.users.models;

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
    private String password;
    @NotBlank
    @Size(min = 8)
    private String newPassword;

}
