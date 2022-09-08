package com.xtsshop.app.features.orders;

import com.xtsshop.app.advices.exception.UnAuthorizationException;
import com.xtsshop.app.db.entities.AppUser;
import com.xtsshop.app.db.entities.Order;
import com.xtsshop.app.db.entities.OrderStatus;
import com.xtsshop.app.features.orders.models.OrderModelAssembler;
import com.xtsshop.app.features.orders.models.OrderRepresentationModel;
import com.xtsshop.app.features.users.TargetUserService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users/{username}/orders")
public class UserOrderController {
    private OrderModelAssembler modelAssembler;
    private TargetUserService targetUserService;

    public UserOrderController(OrderModelAssembler modelAssembler, TargetUserService targetUserService) {
        this.modelAssembler = modelAssembler;
        this.targetUserService = targetUserService;
    }

    @GetMapping
    public CollectionModel<EntityModel<OrderRepresentationModel>> list(@NotBlank @PathVariable String username) throws UnAuthorizationException {
        AppUser user = targetUserService.getUser(username);
        List<Order> orders = user
                .getOrders()
                .stream()
                .filter(order-> order.getStatus() != OrderStatus.WAITING_PAYMENT)
                .collect(Collectors.toList());
        return modelAssembler.toCollectionModel(orders);
    }
}
