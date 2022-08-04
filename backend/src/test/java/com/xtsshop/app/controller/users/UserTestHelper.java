package com.xtsshop.app.controller.users;

import com.xtsshop.app.db.repositories.UserJpaRepository;
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
    private UserJpaRepository userJpaRepository;
    private DevelopmentDataSeed seed;

    public UserTestHelper(UserJpaRepository userJpaRepository, DevelopmentDataSeed seed) {
        this.userJpaRepository = userJpaRepository;
        this.seed = seed;
    }

    public void insertData(){
        seed.insertData();
    }
}
