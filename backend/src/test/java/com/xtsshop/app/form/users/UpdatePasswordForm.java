package com.xtsshop.app.form.users;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UpdatePasswordForm {
    private String oldPassword;
    private String password;

    public UpdatePasswordForm(String oldPassword, String password) {
        this.oldPassword = oldPassword;
        this.password = password;
    }
}
