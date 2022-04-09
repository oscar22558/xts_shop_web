package com.xtsshop.app.service.auth;


import com.xtsshop.app.db.entities.AppUser;
import com.xtsshop.app.db.repositories.UserRepository;
import com.xtsshop.app.form.user.SpringUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserIdentityService {

    private UserRepository userRepository;

    public UserIdentityService(UserRepository userRepository) {
        this.userRepository = userRepository;
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
    @Transactional
    public AppUser getUser(){
        String username = getSpringUser().getUsername();
        return userRepository.findUserByUsername(username);
    }
}
