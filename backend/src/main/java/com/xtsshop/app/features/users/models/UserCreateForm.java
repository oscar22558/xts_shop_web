package com.xtsshop.app.features.users.models;

import com.xtsshop.app.features.users.exceptions.UserRegistryException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.config.core.userdetails.UserDetailsResourceFactoryBean;

import javax.annotation.Nullable;
import javax.validation.constraints.*;

@Getter
@Setter
@NoArgsConstructor
public class UserCreateForm{
    @NotEmpty(message = UserRegistryException.COLUMN_EMPTY)
    @Length(min = 2, message = UserRegistryException.USERNAME_LESS_THAN_2_CHARS)
    @Pattern(regexp = "^([0-9a-zA-Z])([0-9a-zA-Z]*)([0-9a-zA-Z])$", message = UserRegistryException.USERNAME_CONTAINS_INVALID_CHARS)
    private String username;

    @NotEmpty(message = UserRegistryException.COLUMN_EMPTY)
    @Length(min = 8, message = UserRegistryException.PASSWORD_LESS_THAN_8_CHARS)
    private String password;

    @NotEmpty(message = UserRegistryException.COLUMN_EMPTY)
    @Email(message = UserRegistryException.EMAIL_FORMAT_INVALID)
    private String email;
    @Nullable
    @Pattern(regexp = "^[0-9a-zA-Z+]+$", message = UserRegistryException.PHONE_FORMAT_INVALID)
    private String phone;
}
