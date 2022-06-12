package com.xtsshop.app.controller.users;

import com.xtsshop.app.db.repositories.UserRepository;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Getter
@Component("tests.http.users.Util")
public class Util {
    private String route = "/api/users";
    private String updateEmailRoute = "/api/users/email";
    private String updatePhoneRoute = "/api/users/phone";
    private String updatePasswordRoute = "/api/users/password";
    private String oneRoute = "/api/users/{username}";
    private UserRepository userRepository;

    public Util(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
