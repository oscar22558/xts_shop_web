package com.xtsshop.app.controller.users;

import com.xtsshop.app.advices.exception.RecordNotFoundException;
import com.xtsshop.app.controller.users.models.UserRequest;
import com.xtsshop.app.db.entities.AppUser;
import com.xtsshop.app.db.entities.Role;
import com.xtsshop.app.db.entities.RoleType;
import com.xtsshop.app.db.repositories.RoleRepository;
import com.xtsshop.app.db.repositories.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class UsersCRUDService {

    private UserRepository repository;
    private RoleRepository roleRepository;

    public UsersCRUDService(UserRepository repository, RoleRepository roleRepository) {
        this.repository = repository;
        this.roleRepository = roleRepository;
    }

    public AppUser findUserById(Long id) throws RecordNotFoundException{
        return repository.findById(id).orElseThrow(()->new RecordNotFoundException("User of id"+id+" not found."));
    }
    public AppUser findUserByUserName(String username) throws UsernameNotFoundException{
        AppUser user = repository.findUserByUsername(username);
        if(user == null) throw new UsernameNotFoundException("User "+username+" not found.");
        return user;
    };

    public List<AppUser> findAll(){
        return repository.findAll();
    }
    public AppUser create(UserRequest userRequest){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        List<Role> roles = new ArrayList<>();
        roles.add(roleRepository.findByName(RoleType.ROLE_USER.name()));
        return repository.save(userRequest.setEncoder(encoder).setRoles(roles).toEntity());
    }

    public AppUser update(UserRequest userRequest) throws UsernameNotFoundException{
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        AppUser originalUser = findUserByUserName(userRequest.getUsername());
        return repository.save(userRequest.setEncoder(encoder).update(originalUser));
    }
    public AppUser updateUsername(String oldUsername, String username) throws UsernameNotFoundException{
        AppUser user = findUserByUserName(oldUsername);
        user.setUsername(username);
        return repository.save(user);
    }
    public void delete(UserRequest userRequest) throws UsernameNotFoundException {
        AppUser originalUser = findUserByUserName(userRequest.getUsername());
        repository.delete(originalUser);
    }
}
