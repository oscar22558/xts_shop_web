package com.xtsshop.app.features.orders;

import com.xtsshop.app.features.orders.exceptions.OrderStatusUpdateException;
import com.xtsshop.app.features.orders.models.OrderModelAssembler;
import com.xtsshop.app.features.storage.FilePathToUrlConverter;
import com.xtsshop.app.advices.exception.RecordNotFoundException;
import com.xtsshop.app.advices.exception.UnAuthorizationException;
import com.xtsshop.app.db.entities.Order;
import com.xtsshop.app.viewmodels.UpdateRequestViewModel;
import com.xtsshop.app.features.orders.models.OrderRepresentationModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private OrdersService ordersService;
    private OrderModelAssembler modelAssembler;

    public OrderController(OrdersService ordersService, OrderModelAssembler modelAssembler) {
        this.ordersService = ordersService;
        this.modelAssembler = modelAssembler;
    }

    @GetMapping
    public CollectionModel<EntityModel<OrderRepresentationModel>> list(){
        return modelAssembler.toCollectionModel(ordersService.getAllOrders());
    }

    @GetMapping("/{orderId}")
    public EntityModel<OrderRepresentationModel> get(@PathVariable @NotBlank Long orderId) throws RecordNotFoundException, UnAuthorizationException{
        return modelAssembler.toModel(ordersService.getOrder(orderId));
    }

    @PatchMapping("/{orderId}/cancel")
    public ResponseEntity<?> cancel(@PathVariable @NotBlank Long orderId) throws RecordNotFoundException, OrderStatusUpdateException, UnAuthorizationException {
        Order entity = ordersService.cancelOrder(orderId);
        return getUpdateStatusResponse(entity);
    }

    @PatchMapping("/{orderId}/start-processing")
    public ResponseEntity<?> startProcessing(@PathVariable @NotBlank Long orderId) throws RecordNotFoundException, OrderStatusUpdateException, UnAuthorizationException {
        Order entity = ordersService.startProcessing(orderId);
        return getUpdateStatusResponse(entity);
    }

    @PatchMapping("/{orderId}/ship")
    public ResponseEntity<?> ship(@PathVariable @NotBlank Long orderId) throws RecordNotFoundException, OrderStatusUpdateException, UnAuthorizationException {
        Order entity = ordersService.startShipping(orderId);
        return getUpdateStatusResponse(entity);
    }
    @PatchMapping("/{orderId}/finish-shipping")
    public ResponseEntity<?> finishShipping(@PathVariable @NotBlank Long orderId) throws RecordNotFoundException, OrderStatusUpdateException, UnAuthorizationException {
        Order entity = ordersService.finishShipping(orderId);
        return getUpdateStatusResponse(entity);
    }
    private ResponseEntity<?> getUpdateStatusResponse(Order entity){
        return new UpdateRequestViewModel<OrderRepresentationModel, Order>()
                .setEntity(entity)
                .setModelAssembler(modelAssembler)
                .getResponse();
    }
}
