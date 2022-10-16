package com.xtsshop.app.db.seed;

import com.xtsshop.app.db.entities.AppUser;
import com.xtsshop.app.db.repositories.UserJpaRepository;
import com.xtsshop.app.helpers.DateTimeHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.sql.Date;


@Component
public class UserPasswordEncryptor {
    private final UserJpaRepository userJpaRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserPasswordEncryptor(UserJpaRepository userJpaRepository) {
        this.userJpaRepository = userJpaRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public void encrypt(String username){
        Date now = new DateTimeHelper().now();
        AppUser user = userJpaRepository.findUserByUsername(username);
        if(user.getPasswordEncryptedAt() != null){
            return;
        }
        String rawPassword = user.getPassword();
        user.setPassword(passwordEncoder.encode(rawPassword));
        user.setPasswordEncryptedAt(now);
        userJpaRepository.save(user);
    }
}
