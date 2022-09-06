package com.xtsshop.app.features.users.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
public class UserUpdatePasswordForm {
    @NotEmpty(message = UpdatePasswordErrorCode.CANNOT_BE_BLANK)
    private String password;
    @NotEmpty(message = UpdatePasswordErrorCode.CANNOT_BE_BLANK)
    @Length(min = 8, message = UpdatePasswordErrorCode.LENGTH_AT_LEAST_8)
    private String newPassword;

}
