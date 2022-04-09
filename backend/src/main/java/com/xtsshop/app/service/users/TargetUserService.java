package com.xtsshop.app.service.users;

import com.xtsshop.app.advice.exception.UnAuthorizationException;
import com.xtsshop.app.db.entities.AppUser;
import com.xtsshop.app.db.entities.Role;
import com.xtsshop.app.db.entities.RoleType;
import com.xtsshop.app.service.auth.UserIdentityService;
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

    public AppUser getUser(@Nullable String username) throws UnAuthorizationException {
        Set<Role> authedUserRoles = userIdentityService.getUser().getRoles();
        boolean authedUserIsAdmin = authedUserRoles.stream()
                .filter((role)-> role.getName().name() == RoleType.ROLE_ADMIN.name())
                .findFirst()
                .orElse(null) == null;
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
