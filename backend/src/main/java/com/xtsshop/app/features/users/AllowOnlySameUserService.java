package com.xtsshop.app.features.users;

import com.xtsshop.app.features.authentication.UserIdentityService;
import org.springframework.stereotype.Service;

@Service
public class AllowOnlySameUserService extends TargetUserService {
    public AllowOnlySameUserService(UserIdentityService userIdentityService, UsersService usersCRUDService) {
        super(userIdentityService, usersCRUDService);
    }

    public boolean canUserAccess(String username){
        return isSameUser(
                userIdentityService.getUser(),
                usersCRUDService.findUserByUserName(username)
                );
    }

}
