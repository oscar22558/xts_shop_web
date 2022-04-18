package com.xtsshop.app.controller.users;

import com.xtsshop.app.advice.exception.RecordNotFoundException;
import com.xtsshop.app.advice.exception.UnAuthorizationException;
import com.xtsshop.app.assembler.OrderModelAssembler;
import com.xtsshop.app.db.entities.AppUser;
import com.xtsshop.app.db.entities.Order;
import com.xtsshop.app.form.orders.OrderCreateForm;
import com.xtsshop.app.form.orders.OrderListForm;
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

@RestController("users.OrderController")
@RequestMapping("/api/users/orders")
public class OrderController {
    private UserIdentityService userIdentityService;
    private OrderModelAssembler modelAssembler;
    private OrdersService ordersCRUDService;
    private TargetUserService targetUserService;

    public OrderController(UserIdentityService userIdentityService, OrderModelAssembler modelAssembler, OrdersService ordersCRUDService, TargetUserService targetUserService) {
        this.userIdentityService = userIdentityService;
        this.modelAssembler = modelAssembler;
        this.ordersCRUDService = ordersCRUDService;
        this.targetUserService = targetUserService;
    }

    @GetMapping
    public CollectionModel<EntityModel<OrderModel>> list(@Valid @RequestBody OrderListForm form) throws UnAuthorizationException {
        AppUser user = targetUserService.getUser(form.getUsername());
        return modelAssembler.toCollectionModel(user.getOrders());
    }
    @PostMapping
    public ResponseEntity<?> create(OrderCreateForm form) throws RecordNotFoundException, UnAuthorizationException {
        Order entity = ordersCRUDService.create(form.toRequest());
        return new CreateResponseBuilder<OrderModel, Order>()
                .setEntity(entity)
                .setModelAssembler(modelAssembler)
                .build();
    }

}
