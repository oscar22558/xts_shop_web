package com.xtsshop.app.form.user;

import com.xtsshop.app.db.entities.AppUser;
import com.xtsshop.app.form.Form;
import com.xtsshop.app.request.UserRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.annotation.Nullable;
import javax.validation.constraints.*;

@Getter
@Setter
@NoArgsConstructor
public class UserCreateForm implements Form<UserRequest, AppUser> {
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

    @Override
    public UserRequest toRequest() {
        return new UserRequest(
                username,
                password,
                email,
                phone
        );
    }
}
