package com.xtsshop.app.form.users;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UpdateUsernameForm {
    private String password;
    private String phone;

    public UpdateUsernameForm(String password, String phone) {
        this.password = password;
        this.phone = phone;
    }
}
