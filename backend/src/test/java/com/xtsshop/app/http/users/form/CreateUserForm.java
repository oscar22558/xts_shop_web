package com.xtsshop.app.http.users.form;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreateUserForm {
    private String username;
    private String password;
    private String email;
    private String phone;

    public CreateUserForm(String username, String password, String email, String phone) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
    }
}
