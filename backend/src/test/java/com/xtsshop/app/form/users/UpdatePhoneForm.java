package com.xtsshop.app.form.users;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UpdatePhoneForm {
    private String password;
    private String phone;

    public UpdatePhoneForm(String password, String phone) {
        this.password = password;
        this.phone = phone;
    }
}
