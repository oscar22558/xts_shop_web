package com.xtsshop.app.controller;

import com.xtsshop.app.advice.exception.OrderStatusUpdateException;
import com.xtsshop.app.advice.exception.RecordNotFoundException;
import com.xtsshop.app.advice.exception.UnAuthorizationException;
import com.xtsshop.app.assembler.OrderModelAssembler;
import com.xtsshop.app.db.entities.Order;
import com.xtsshop.app.form.orders.PaymentCreateForm;
import com.xtsshop.app.response.UpdateResponseBuilder;
import com.xtsshop.app.service.orders.OrdersCRUDService;
import com.xtsshop.app.service.users.TargetUserService;
import com.xtsshop.app.viewmodel.OrderViewModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private OrdersCRUDService ordersCRUDService;
    private OrderModelAssembler modelAssembler;
    public OrderController(OrdersCRUDService ordersCRUDService, OrderModelAssembler modelAssembler) {
        this.ordersCRUDService = ordersCRUDService;
        this.modelAssembler = modelAssembler;
    }

    @GetMapping
    public CollectionModel<EntityModel<OrderViewModel>> list(){
        return modelAssembler.toCollectionModel(ordersCRUDService.all());
    }

    @GetMapping("/{orderId}")
    public EntityModel<OrderViewModel> get(@PathVariable @NotBlank Long orderId) throws RecordNotFoundException, UnAuthorizationException{
        return modelAssembler.toModel(ordersCRUDService.get(orderId));
    }
    @PatchMapping("/{orderId}/cancel")
    public ResponseEntity<?> cancel(@PathVariable @NotBlank Long orderId) throws RecordNotFoundException, OrderStatusUpdateException, UnAuthorizationException {
        Order entity = ordersCRUDService.cancel(orderId);
        return getUpdateStatusResponse(entity);
    }

    @PatchMapping("/{orderId}/pay")
    public ResponseEntity<?> pay(@PathVariable @NotBlank Long orderId, @Valid PaymentCreateForm form) throws RecordNotFoundException, OrderStatusUpdateException, UnAuthorizationException {
        Order entity = ordersCRUDService.pay(orderId, form.toRequest());
        return getUpdateStatusResponse(entity);
    }

    @PatchMapping("/{orderId}/start-processing")
    public ResponseEntity<?> startProcessing(@PathVariable @NotBlank Long orderId) throws RecordNotFoundException, OrderStatusUpdateException, UnAuthorizationException {
        Order entity = ordersCRUDService.startProcessing(orderId);
        return getUpdateStatusResponse(entity);
    }

    @PatchMapping("/{orderId}/ship")
    public ResponseEntity<?> ship(@PathVariable @NotBlank Long orderId) throws RecordNotFoundException, OrderStatusUpdateException, UnAuthorizationException {
        Order entity = ordersCRUDService.ship(orderId);
        return getUpdateStatusResponse(entity);
    }
    @PatchMapping("/{orderId}/finish-shipping")
    public ResponseEntity<?> finishShipping(@PathVariable @NotBlank Long orderId) throws RecordNotFoundException, OrderStatusUpdateException, UnAuthorizationException {
        Order entity = ordersCRUDService.finishShipping(orderId);
        return getUpdateStatusResponse(entity);
    }
    private ResponseEntity<?> getUpdateStatusResponse(Order entity){
        return new UpdateResponseBuilder<OrderViewModel, Order>()
                .setEntity(entity)
                .setModelAssembler(modelAssembler)
                .build();
    }
}
