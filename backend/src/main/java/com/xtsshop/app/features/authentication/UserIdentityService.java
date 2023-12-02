package com.xtsshop.app.features.authentication;


import com.xtsshop.app.db.entities.AppUser;
import com.xtsshop.app.db.repositories.UserJpaRepository;
import com.xtsshop.app.features.users.models.SpringUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserIdentityService {

    private UserJpaRepository userJpaRepository;

    public UserIdentityService(UserJpaRepository userJpaRepository) {
        this.userJpaRepository = userJpaRepository;
    }

    public boolean isAnonymous() {
        return getSpringUser() == null;
    }
    public SpringUser getSpringUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        return principal.equals("anonymousUser")
                ? null
                : (SpringUser) principal;
    }

    public AppUser getUser(){
        String username = getSpringUser().getUsername();
        return userJpaRepository.findUserByUsername(username);
    }
}
