package com.xtsshop.app.features.users.validations;

import com.xtsshop.app.db.repositories.UserJpaRepository;
import com.xtsshop.app.features.users.exceptions.UsernameExistsException;
import com.xtsshop.app.features.users.models.UserCreateForm;
import org.springframework.stereotype.Component;

@Component
public class RegistryValidation {
    private UserJpaRepository userJpaRepository;

    public RegistryValidation(UserJpaRepository userJpaRepository) {
        this.userJpaRepository = userJpaRepository;
    }

    public void validate(UserCreateForm userCreateForm){
        boolean isUsernameExists = userJpaRepository.findUserByUsername(userCreateForm.getUsername()) != null;
        if(isUsernameExists){
            throw new UsernameExistsException();
        }
    }
}
