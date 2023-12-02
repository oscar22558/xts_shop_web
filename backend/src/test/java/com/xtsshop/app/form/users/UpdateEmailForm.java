package com.xtsshop.app.form.users;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UpdateEmailForm {
    private String password;
    private String email;

    public UpdateEmailForm(String password, String email) {
        this.password = password;
        this.email = email;
    }
}
