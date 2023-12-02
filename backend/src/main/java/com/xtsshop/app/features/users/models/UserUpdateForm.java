package com.xtsshop.app.features.users.models;

import com.xtsshop.app.validations.groups.ColumnEmpty;
import com.xtsshop.app.validations.groups.ColumnLength;
import com.xtsshop.app.validations.groups.ColumnPattern;
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
    @NotBlank(groups = ColumnEmpty.class, message = UserErrorCode.EMAIL_EMPTY)
    @Size(min = 2, groups = ColumnLength.class, message = UserErrorCode.USERNAME_TOO_SHORT)
    @Pattern(regexp = "^([0-9a-zA-Z])([0-9a-zA-Z@_.-]*)([0-9a-zA-Z])$", groups = ColumnPattern.class, message = UserErrorCode.USERNAME_INVALID)
    private String username;

    @NotBlank(groups = ColumnEmpty.class, message = UserErrorCode.EMAIL_EMPTY)
    @Email(groups = ColumnPattern.class, message = UserErrorCode.EMAIL_INVALID_FORMAT)
    private String email;

    @NotBlank(groups = ColumnEmpty.class, message = UserErrorCode.PHONE_EMPTY)
    private String phone;
}
