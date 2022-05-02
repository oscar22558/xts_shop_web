package com.xtsshop.app.controller.users;

import com.xtsshop.app.advice.exception.OrderStatusUpdateException;
import com.xtsshop.app.advice.exception.RecordNotFoundException;
import com.xtsshop.app.advice.exception.UnAuthorizationException;
import com.xtsshop.app.assembler.OrderModelAssembler;
import com.xtsshop.app.db.entities.AppUser;
import com.xtsshop.app.db.entities.Order;
import com.xtsshop.app.db.entities.payment.Payment;
import com.xtsshop.app.db.repositories.CategoryRepository;
import com.xtsshop.app.form.orders.OrderCreateForm;
import com.xtsshop.app.form.orders.OrderListForm;
import com.xtsshop.app.request.orders.PaymentCreateRequest;
import com.xtsshop.app.response.CreateResponseBuilder;
import com.xtsshop.app.service.auth.UserIdentityService;
import com.xtsshop.app.service.orders.OrdersService;
import com.xtsshop.app.service.users.TargetUserService;
import com.xtsshop.app.viewmodel.OrderModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController("users.OrderController")
@RequestMapping("/api/users/orders")
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
    public CollectionModel<EntityModel<OrderModel>> list(@Valid @RequestBody OrderListForm form) throws UnAuthorizationException {
        AppUser user = targetUserService.getUser(form.getUsername());
        return modelAssembler.toCollectionModel(user.getOrders());
    }
    @PostMapping
    public ResponseEntity<?> place(@Valid @RequestBody OrderCreateForm form) throws RecordNotFoundException, UnAuthorizationException {
        Order entity = ordersService.create(form.toRequest());
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
        return new CreateResponseBuilder<OrderModel, Order>()
                .setEntity(entity)
                .setModelAssembler(modelAssembler)
                .build();
    }

}
