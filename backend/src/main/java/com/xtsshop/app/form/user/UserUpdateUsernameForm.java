package com.xtsshop.app.form.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class UserUpdateUsernameForm {
    @NotBlank
    private String password;
    @NotBlank
    @Size(min = 2)
    @Pattern(regexp = "^([0-9a-zA-Z])([0-9a-zA-Z@_.-]*)([0-9a-zA-Z])$")
    private String username;

}
