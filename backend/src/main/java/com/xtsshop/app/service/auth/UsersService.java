package com.xtsshop.app.service.auth;

import com.xtsshop.app.db.entities.AppUser;
import com.xtsshop.app.db.repositories.UserRepository;
import com.xtsshop.app.request.UserRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Nullable;

@Service
public class UsersService {

    private UserRepository repository;
    public UsersService(UserRepository repository){
        this.repository = repository;
    }

    public @Nullable AppUser getUserByUserName(String username){

        return repository.findUserByUsername(username);
    };

    public AppUser create(UserRequest userRequest){
        return repository.save(userRequest.toEntity());
    }
}
