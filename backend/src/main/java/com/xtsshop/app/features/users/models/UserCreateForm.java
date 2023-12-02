package com.xtsshop.app.features.users.models;

import com.xtsshop.app.features.users.exceptions.RegistryFormInvalidCode;
import com.xtsshop.app.validations.groups.ColumnEmpty;
import com.xtsshop.app.validations.groups.ColumnLength;
import com.xtsshop.app.validations.groups.ColumnPattern;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.annotation.Nullable;
import javax.validation.constraints.*;


@Getter
@Setter
@NoArgsConstructor
public class UserCreateForm{
    @NotEmpty(message = RegistryFormInvalidCode.COLUMN_EMPTY, groups = ColumnEmpty.class)
    @Size(min = 2, message = RegistryFormInvalidCode.USERNAME_LESS_THAN_2_CHARS, groups = ColumnLength.class)
    @Pattern(regexp = "^([0-9a-zA-Z])([0-9a-zA-Z]*)([0-9a-zA-Z])$", message = RegistryFormInvalidCode.USERNAME_CONTAINS_INVALID_CHARS, groups = ColumnPattern.class)
    private String username;

    @NotEmpty(message = RegistryFormInvalidCode.COLUMN_EMPTY, groups = ColumnEmpty.class)
    @Size(min = 8, message = RegistryFormInvalidCode.PASSWORD_LESS_THAN_8_CHARS, groups = ColumnLength.class)
    private String password;

    @NotEmpty(message = RegistryFormInvalidCode.COLUMN_EMPTY, groups = ColumnEmpty.class)
    @Email(message = RegistryFormInvalidCode.EMAIL_FORMAT_INVALID, groups = ColumnPattern.class)
    private String email;

    @Nullable
    @Pattern(regexp = "^[0-9a-zA-Z+]+$", message = RegistryFormInvalidCode.PHONE_FORMAT_INVALID, groups = ColumnPattern.class)
    private String phone;
}
