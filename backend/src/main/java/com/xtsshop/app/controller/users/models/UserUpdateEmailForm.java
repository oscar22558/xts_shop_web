package com.xtsshop.app.controller.users.models;

import jdk.jfr.StackTrace;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.annotation.Nullable;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Setter
@Getter
@NoArgsConstructor
public class UserUpdateEmailForm {
    @NotBlank
    private String password;
    @NotBlank
    @Email
    private String email;

}
