package com.xtsshop.app.features.users;

import com.xtsshop.app.db.repositories.UserJpaRepository;
import com.xtsshop.app.db.seed.DevDataSeed;
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
    private DevDataSeed seed;

    public UserTestHelper(UserJpaRepository userJpaRepository, DevDataSeed seed) {
        this.userJpaRepository = userJpaRepository;
        this.seed = seed;
    }

    public void insertData(){
        seed.insertData();
    }
}
