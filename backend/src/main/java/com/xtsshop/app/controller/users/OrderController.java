package com.xtsshop.app.controller.users;

import com.xtsshop.app.advice.exception.OrderStatusUpdateException;
import com.xtsshop.app.advice.exception.RecordNotFoundException;
import com.xtsshop.app.advice.exception.UnAuthorizationException;
import com.xtsshop.app.assembler.OrderModelAssembler;
import com.xtsshop.app.db.entities.AppUser;
import com.xtsshop.app.db.entities.Order;
import com.xtsshop.app.form.orders.OrderCreateForm;
import com.xtsshop.app.domain.request.orders.OrderCreateRequest;
import com.xtsshop.app.domain.request.orders.PaymentCreateRequest;
import com.xtsshop.app.viewmodel.CreateRequestViewModel;
import com.xtsshop.app.domain.service.auth.UserIdentityService;
import com.xtsshop.app.domain.service.orders.OrdersService;
import com.xtsshop.app.domain.service.users.TargetUserService;
import com.xtsshop.app.assembler.models.OrderRepresentationModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.Optional;

@RestController("users.OrderController")
@RequestMapping("/api/users/{username}/orders")
public class OrderController {
    private UserIdentityService userIdentityService;
    private OrderModelAssembler modelAssembler;
    private OrdersService ordersService;
    private TargetUserService targetUserService;

    public OrderController(UserIdentityService userIdentityService, OrderModelAssembler modelAssembler, OrdersService ordersService, TargetUserService targetUserService) {
        this.userIdentityService = userIdentityService;
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
    public ResponseEntity<?> place(@NotBlank @PathVariable String username, @Valid @RequestBody OrderCreateForm form) throws RecordNotFoundException, UnAuthorizationException {
        OrderCreateRequest orderCreateRequest = form.toRequest();
        orderCreateRequest.setUsername(username);
        Order entity = ordersService.create(orderCreateRequest);
        Optional.ofNullable(form.getPayment()).ifPresent(paymentCreateForm -> {
            PaymentCreateRequest request = paymentCreateForm.toRequest(
                    ordersService.getItemPriceTotal(entity)
            );
            try {
                ordersService.pay(entity.getId(), request);
            } catch (RecordNotFoundException | OrderStatusUpdateException | UnAuthorizationException e) {
                throw new RuntimeException(e);
            }
        });
        return new CreateRequestViewModel<OrderRepresentationModel, Order>()
                .setEntity(entity)
                .setModelAssembler(modelAssembler)
                .getResponse();
    }

}
