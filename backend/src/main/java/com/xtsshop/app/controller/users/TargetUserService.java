package com.xtsshop.app.controller.users;

import com.xtsshop.app.advices.exception.UnAuthorizationException;
import com.xtsshop.app.controller.authentication.UserIdentityService;
import com.xtsshop.app.db.entities.AppUser;
import com.xtsshop.app.db.entities.Role;
import com.xtsshop.app.db.entities.RoleType;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Set;

@Service
public class TargetUserService {
    protected UserIdentityService userIdentityService;
    protected UsersService usersCRUDService;

    public TargetUserService(UserIdentityService userIdentityService, UsersService usersCRUDService) {
        this.userIdentityService = userIdentityService;
        this.usersCRUDService = usersCRUDService;
    }

    public boolean authedUserIsAdmin(){
        Set<Role> authedUserRoles = userIdentityService.getUser().getRoles();
        return authedUserRoles.stream()
                .filter((role)-> role.getName().name().equals(RoleType.ROLE_ADMIN.name()))
                .findFirst()
                .orElse(null) != null;
    }
    public boolean isSameUser(AppUser user1, AppUser user2){
        return Objects.equals(user1.getUsername(), user2.getUsername());
    }
    public boolean canUserAccess(String username){
        boolean authedUserIsAdmin = authedUserIsAdmin();
        boolean isSameUser = isSameUser(
                userIdentityService.getUser(),
                usersCRUDService.findUserByUserName(username)
                );
        return authedUserIsAdmin || isSameUser;
    }
    public AppUser getUser(String username) throws UnAuthorizationException{
        boolean userCanAccess = canUserAccess(username);
        if(userCanAccess){
            return usersCRUDService.findUserByUserName(username);
        }else {
            throw new UnAuthorizationException();
        }
    }
}
