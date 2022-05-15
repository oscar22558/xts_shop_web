package com.xtsshop.app.service.users;

import com.xtsshop.app.advice.exception.UnAuthorizationException;
import com.xtsshop.app.db.entities.AppUser;
import com.xtsshop.app.db.entities.Role;
import com.xtsshop.app.db.entities.RoleType;
import com.xtsshop.app.service.auth.UserIdentityService;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Set;

@Service
public class AllowOnlySameUserService extends TargetUserService {
    public AllowOnlySameUserService(UserIdentityService userIdentityService, UsersCRUDService usersCRUDService) {
        super(userIdentityService, usersCRUDService);
    }

    public boolean canUserAccess(String username){
        return isSameUser(
                userIdentityService.getUser(),
                usersCRUDService.findUserByUserName(username)
                );
    }

}
