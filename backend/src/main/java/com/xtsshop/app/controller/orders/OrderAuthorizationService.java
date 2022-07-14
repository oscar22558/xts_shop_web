package com.xtsshop.app.controller.orders;

import com.xtsshop.app.advices.exception.UnAuthorizationException;
import com.xtsshop.app.controller.authentication.UserIdentityService;
import com.xtsshop.app.db.entities.Order;
import com.xtsshop.app.db.entities.RoleType;
import org.springframework.stereotype.Service;

@Service
public class OrderAuthorizationService {
    private UserIdentityService userIdentityService;

    public OrderAuthorizationService(UserIdentityService userIdentityService) {
        this.userIdentityService = userIdentityService;
    }

    public boolean isAuthorized(Order order) throws UnAuthorizationException {
        boolean userIsAdmin = userIdentityService.getUser().getRoles()
                .stream().filter(role->role.getName() == RoleType.ROLE_ADMIN)
                .findFirst()
                .orElse(null) != null;
        boolean result = userIsAdmin || order.getUser().getUsername().equals(userIdentityService.getSpringUser().getUsername());
        if(!result) throw new UnAuthorizationException("Access forbidden");
        return true;
    }
}
