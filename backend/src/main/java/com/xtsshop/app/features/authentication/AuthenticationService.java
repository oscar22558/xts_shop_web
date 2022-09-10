package com.xtsshop.app.features.authentication;

import com.xtsshop.app.advices.exception.UnAuthenticationException;
import com.xtsshop.app.features.users.models.SpringUser;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private AuthenticationManager authenticationManager;
    private UserIdentityService userIdentityService;

    public AuthenticationService(AuthenticationManager authenticationManager, UserIdentityService userIdentityService) {
        this.authenticationManager = authenticationManager;
        this.userIdentityService = userIdentityService;
    }

    public UserDetails confirmPassword(String password) throws UnAuthenticationException {
        if(userIdentityService.isAnonymous()) throw new UnAuthenticationException("Please sign-in first.");
        SpringUser user = userIdentityService.getSpringUser();
        Authentication authentication =
                new UsernamePasswordAuthenticationToken(user.getUsername(), password);
        authentication = authenticationManager.authenticate(authentication);
        return (UserDetails) authentication.getPrincipal();
    }
}
