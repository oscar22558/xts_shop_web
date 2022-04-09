package com.xtsshop.app.http.users.form;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
