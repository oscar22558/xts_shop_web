package com.xtsshop.app.http;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class AuthForm {
    private String username;
    private String password;

    public AuthForm(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
