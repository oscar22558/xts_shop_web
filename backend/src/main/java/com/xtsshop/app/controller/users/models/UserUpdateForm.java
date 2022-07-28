package com.xtsshop.app.controller.users.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@NoArgsConstructor
@Setter
@Getter
public class UserUpdateForm {
    @NotBlank
    @Size(min = 2)
    @Pattern(regexp = "^([0-9a-zA-Z])([0-9a-zA-Z@_.-]*)([0-9a-zA-Z])$")
    private String username;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String phone;
}
