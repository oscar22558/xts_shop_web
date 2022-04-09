package com.xtsshop.app.http.users.form;

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
