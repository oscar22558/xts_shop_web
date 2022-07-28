package com.xtsshop.app.controller.users.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.annotation.Nullable;
import javax.validation.constraints.*;

@Getter
@Setter
@NoArgsConstructor
public class UserCreateForm{
    @NotBlank
    @Size(min = 2)
    @Pattern(regexp = "^([0-9a-zA-Z])([0-9a-zA-Z]*)([0-9a-zA-Z])$")
    private String username;
    @NotBlank
    @Length(min = 8)
    private String password;
    @NotBlank
    @Email
    private String email;
    @Nullable
    @Pattern(regexp = "^[0-9a-zA-Z+]+$")
    private String phone;
}
