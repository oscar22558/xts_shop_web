package com.xtsshop.app.form.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.annotation.Nullable;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Setter
@Getter
@NoArgsConstructor
public class UserUpdatePhoneForm {
    @NotBlank
    private String password;
    @NotBlank
    private String phone;

}
