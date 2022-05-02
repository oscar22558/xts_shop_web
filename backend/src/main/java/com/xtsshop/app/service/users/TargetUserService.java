package com.xtsshop.app.service.users;

import com.xtsshop.app.advice.UsernameNotFoundAdvice;
import com.xtsshop.app.advice.exception.UnAuthorizationException;
import com.xtsshop.app.db.entities.AppUser;
import com.xtsshop.app.db.entities.Role;
import com.xtsshop.app.db.entities.RoleType;
import com.xtsshop.app.service.auth.UserIdentityService;
import com.xtsshop.app.service.orders.OrdersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Nullable;
import java.util.Set;

@Service
public class TargetUserService {
    private UserIdentityService userIdentityService;
    private UsersCRUDService usersCRUDService;

    public TargetUserService(UserIdentityService userIdentityService, UsersCRUDService usersCRUDService) {
        this.userIdentityService = userIdentityService;
        this.usersCRUDService = usersCRUDService;
    }

    public AppUser getUser(@Nullable String username) throws UnAuthorizationException, UsernameNotFoundException {
        Set<Role> authedUserRoles = userIdentityService.getUser().getRoles();
        boolean authedUserIsAdmin = authedUserRoles.stream()
                .filter((role)-> role.getName().name().equals(RoleType.ROLE_ADMIN.name()))
                .findFirst()
                .orElse(null) != null;
        if(authedUserIsAdmin){
            return usersCRUDService.findUserByUserName(username);
        }else{
            if(username == null){
                return userIdentityService.getUser();
            }else{
                throw new UnAuthorizationException("Access forbidden");
            }
        }
    }
}
