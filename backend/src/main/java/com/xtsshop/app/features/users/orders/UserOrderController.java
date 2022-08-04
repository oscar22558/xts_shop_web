package com.xtsshop.app.features.users.orders;

import com.xtsshop.app.advices.exception.UnAuthorizationException;
import com.xtsshop.app.features.orders.OrdersService;
import com.xtsshop.app.features.orders.models.OrderCreateRequest;
import com.xtsshop.app.features.orders.models.OrderModelAssembler;
import com.xtsshop.app.features.orders.models.OrderRepresentationModel;
import com.xtsshop.app.features.orders.models.PaymentCreateRequest;
import com.xtsshop.app.features.users.TargetUserService;
import com.xtsshop.app.features.users.cart.models.OrderCreateForm;
import com.xtsshop.app.db.entities.AppUser;
import com.xtsshop.app.db.entities.Order;
import com.xtsshop.app.viewmodels.CreateRequestViewModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.Optional;

@RestController
@RequestMapping("/api/users/{username}/orders")
public class UserOrderController {
    private OrderModelAssembler modelAssembler;
    private OrdersService ordersService;
    private TargetUserService targetUserService;

    public UserOrderController(OrderModelAssembler modelAssembler, OrdersService ordersService, TargetUserService targetUserService) {
        this.modelAssembler = modelAssembler;
        this.ordersService = ordersService;
        this.targetUserService = targetUserService;
    }

    @GetMapping
    public CollectionModel<EntityModel<OrderRepresentationModel>> list(@NotBlank @PathVariable String username) throws UnAuthorizationException {
        AppUser user = targetUserService.getUser(username);
        return modelAssembler.toCollectionModel(user.getOrders());
    }

    @PostMapping
    public ResponseEntity<?> place(@NotBlank @PathVariable String username, @Valid @RequestBody OrderCreateForm form){
        OrderCreateRequest orderCreateRequest = form.toRequest();
        orderCreateRequest.setUsername(username);
        Order entity = ordersService.create(orderCreateRequest);
        Optional.ofNullable(form.getPayment()).ifPresent(paymentCreateForm -> {
            PaymentCreateRequest request = paymentCreateForm.toRequest();
            ordersService.pay(entity.getId(), request);
        });

        return new CreateRequestViewModel<OrderRepresentationModel, Order>()
                .setEntity(entity)
                .setModelAssembler(modelAssembler)
                .getResponse();
    }
}
