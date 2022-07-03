package com.xtsshop.app.controller.users;

import com.xtsshop.app.db.repositories.UserRepository;
import com.xtsshop.app.db.seed.DevelopmentDataSeed;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Getter
@Component
public class UserTestHelper {
    private String route = "/api/users";
    private String updateEmailRoute = "/api/users/email";
    private String updatePhoneRoute = "/api/users/phone";
    private String updatePasswordRoute = "/api/users/password";
    private String oneRoute = "/api/users/{username}";
    private UserRepository userRepository;
    private DevelopmentDataSeed seed;

    public UserTestHelper(UserRepository userRepository, DevelopmentDataSeed seed) {
        this.userRepository = userRepository;
        this.seed = seed;
    }

    public void insertData(){
        seed.insertData();
    }
}
