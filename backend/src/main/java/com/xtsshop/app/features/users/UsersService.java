package com.xtsshop.app.features.users;

import com.xtsshop.app.features.authentication.AuthenticationService;
import com.xtsshop.app.features.authentication.UserIdentityService;
import com.xtsshop.app.features.users.models.UserCreateForm;
import com.xtsshop.app.features.users.models.UserUpdateForm;
import com.xtsshop.app.features.users.models.UserUpdatePasswordForm;
import com.xtsshop.app.db.entities.AppUser;
import com.xtsshop.app.db.entities.Role;
import com.xtsshop.app.db.entities.RoleType;
import com.xtsshop.app.db.repositories.RoleJpaRepository;
import com.xtsshop.app.db.repositories.UserJpaRepository;
import com.xtsshop.app.helpers.DateTimeHelper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UsersService {

    private UserJpaRepository repository;
    private RoleJpaRepository roleJpaRepository;
    private AuthenticationService authenticationService;
    private UserIdentityService userIdentityService;
    private BCryptPasswordEncoder encoder;

    public UsersService(UserJpaRepository repository, RoleJpaRepository roleJpaRepository, AuthenticationService authenticationService, UserIdentityService userIdentityService) {
        this.repository = repository;
        this.roleJpaRepository = roleJpaRepository;
        this.authenticationService = authenticationService;
        this.userIdentityService = userIdentityService;
        this.encoder = new BCryptPasswordEncoder();
    }

    public AppUser findUserByUserName(String username) throws UsernameNotFoundException{
        AppUser user = repository.findUserByUsername(username);
        if(user == null) throw new UsernameNotFoundException("User "+username+" not found.");
        return user;
    };

    public List<AppUser> findAll(){
        return repository.findAll();
    }

    public AppUser create(UserCreateForm userCreateForm){
        Date now = getCurrentTimestamp();
        Set<Role> roles = new HashSet<>();
        roles.add(roleJpaRepository.findByName(RoleType.ROLE_USER.name()));
        AppUser newUser = new AppUser(
                now,
                now,
                userCreateForm.getUsername(),
                encoder.encode(userCreateForm.getPassword()),
                userCreateForm.getEmail(),
                userCreateForm.getPhone()
        );
        newUser.setRoles(roles);
        newUser.setAddresses(new ArrayList<>());
        newUser.setOrders(new ArrayList<>());
        return repository.save(newUser);
    }

    public AppUser update(UserUpdateForm updateForm) throws UsernameNotFoundException{
        Date now = getCurrentTimestamp();
        AppUser user = getAuthenticatedUser();
        user.setUsername(updateForm.getUsername());
        user.setEmail(updateForm.getEmail());
        user.setPhone(updateForm.getPhone());
        user.setUpdatedAt(now);
        return repository.save(user);
    }

    public AppUser updatePassword(UserUpdatePasswordForm updatePasswordForm){
        authorizeUser(updatePasswordForm.getPassword());
        Date now = getCurrentTimestamp();
        AppUser user = getAuthenticatedUser();
        String encryptedNewPassword = encoder.encode(updatePasswordForm.getNewPassword());
        user.setPassword(encryptedNewPassword);
        user.setUpdatedAt(now);
        return repository.save(user);
    }

    public void delete(String username) throws UsernameNotFoundException {
        AppUser user = findUserByUserName(username);
        repository.delete(user);
    }

    private void authorizeUser(String password){
        authenticationService.confirmPassword(password).getUsername();
    }

    private AppUser getAuthenticatedUser(){
        return userIdentityService.getUser();
    }

    private Date getCurrentTimestamp(){
        return new DateTimeHelper().now();
    }
}
