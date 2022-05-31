package com.xtsshop.app.controller;

import com.xtsshop.app.advice.exception.OrderStatusUpdateException;
import com.xtsshop.app.advice.exception.RecordNotFoundException;
import com.xtsshop.app.advice.exception.UnAuthorizationException;
import com.xtsshop.app.assembler.OrderModelAssembler;
import com.xtsshop.app.db.entities.Order;
import com.xtsshop.app.form.orders.PaymentCreateForm;
import com.xtsshop.app.response.UpdateResponseBuilder;
import com.xtsshop.app.domain.service.orders.OrdersService;
import com.xtsshop.app.domain.service.storage.StorageService;
import com.xtsshop.app.viewmodel.OrderModel;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private OrdersService ordersService;
    private OrderModelAssembler modelAssembler;
    public OrderController(OrdersService ordersService, OrderModelAssembler modelAssembler, @Qualifier("ImageStorageService") StorageService storageService) {
        this.ordersService = ordersService;
        this.modelAssembler = modelAssembler;
        modelAssembler.setStorageService(storageService);
    }

    @GetMapping
    public CollectionModel<EntityModel<OrderModel>> list(){
        return modelAssembler.toCollectionModel(ordersService.all());
    }

    @GetMapping("/{orderId}")
    public EntityModel<OrderModel> get(@PathVariable @NotBlank Long orderId) throws RecordNotFoundException, UnAuthorizationException{
        return modelAssembler.toModel(ordersService.get(orderId));
    }
    @PatchMapping("/{orderId}/cancel")
    public ResponseEntity<?> cancel(@PathVariable @NotBlank Long orderId) throws RecordNotFoundException, OrderStatusUpdateException, UnAuthorizationException {
        Order entity = ordersService.cancel(orderId);
        return getUpdateStatusResponse(entity);
    }

    @PatchMapping("/{orderId}/pay")
    public ResponseEntity<?> pay(@PathVariable @NotBlank Long orderId, @Valid @RequestBody PaymentCreateForm form) throws RecordNotFoundException, OrderStatusUpdateException, UnAuthorizationException {
        float total = ordersService.getItemPriceTotal(ordersService.get(orderId));
        Order entity = ordersService.pay(orderId, form.toRequest(total));
        return getUpdateStatusResponse(entity);
    }

    @PatchMapping("/{orderId}/start-processing")
    public ResponseEntity<?> startProcessing(@PathVariable @NotBlank Long orderId) throws RecordNotFoundException, OrderStatusUpdateException, UnAuthorizationException {
        Order entity = ordersService.startProcessing(orderId);
        return getUpdateStatusResponse(entity);
    }

    @PatchMapping("/{orderId}/ship")
    public ResponseEntity<?> ship(@PathVariable @NotBlank Long orderId) throws RecordNotFoundException, OrderStatusUpdateException, UnAuthorizationException {
        Order entity = ordersService.ship(orderId);
        return getUpdateStatusResponse(entity);
    }
    @PatchMapping("/{orderId}/finish-shipping")
    public ResponseEntity<?> finishShipping(@PathVariable @NotBlank Long orderId) throws RecordNotFoundException, OrderStatusUpdateException, UnAuthorizationException {
        Order entity = ordersService.finishShipping(orderId);
        return getUpdateStatusResponse(entity);
    }
    private ResponseEntity<?> getUpdateStatusResponse(Order entity){
        return new UpdateResponseBuilder<OrderModel, Order>()
                .setEntity(entity)
                .setModelAssembler(modelAssembler)
                .build();
    }
}
