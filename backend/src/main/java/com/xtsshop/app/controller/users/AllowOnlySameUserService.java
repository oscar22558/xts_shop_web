package com.xtsshop.app.controller.users;

import com.xtsshop.app.controller.authentication.UserIdentityService;
import org.springframework.stereotype.Service;

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
