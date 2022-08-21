package com.xtsshop.app.features.orders;

import com.xtsshop.app.advices.exception.RecordNotFoundException;
import com.xtsshop.app.advices.exception.UnAuthorizationException;
import com.xtsshop.app.db.entities.Order;
import com.xtsshop.app.db.entities.OrderStatus;
import com.xtsshop.app.db.repositories.OrderJpaRepository;
import com.xtsshop.app.features.orders.exceptions.OrderStatusUpdateException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdersService {
    private OrderJpaRepository repository;
    private OrderAuthorizationService orderAuthorizationService;

    public OrdersService(OrderJpaRepository repository, OrderAuthorizationService orderAuthorizationService) {
        this.repository = repository;
        this.orderAuthorizationService = orderAuthorizationService;
    }

    public List<Order> getAllOrders(){
        return repository.findAll();
    }

    public Order getOrder(Long id) throws RecordNotFoundException, UnAuthorizationException {
        Order order =  repository.findById(id).orElseThrow(()->new RecordNotFoundException("Order with id "+id+" not found."));
        orderAuthorizationService.isAuthorized(order);
        return order;
    }

    public Order cancelOrder(Long id){
        Order order = getOrder(id);
        if(order.getStatus() == OrderStatus.SHIPPED || order.getStatus() == OrderStatus.SHIPPING)
            throw new OrderStatusUpdateException("Order cannot be canceled.");
        order.setStatus(OrderStatus.CANCELED);
        order.getOrderedItems().forEach(orderedItem->{
            int updatedItemStock = orderedItem.getItem().getStock() + orderedItem.getQuantity();
            orderedItem.getItem().setStock(updatedItemStock);
        });
        return repository.save(order);
    }

    public Order startProcessing(Long id){
        Order order = getOrder(id);
        if(order.getStatus() == OrderStatus.WAITING_PAYMENT){
            throw new OrderStatusUpdateException("Order is not paid.");
        }else if(order.getStatus() == OrderStatus.SHIPPING){
            throw new OrderStatusUpdateException("Order is shipping.");
        }else if(order.getStatus() == OrderStatus.SHIPPED){
            throw new OrderStatusUpdateException("Order is shipped.");
        }
        order.setStatus(OrderStatus.PROCESSING);
        return repository.save(order);
    }

    public Order startShipping(Long id){
        Order order = getOrder(id);
        if(order.getStatus() == OrderStatus.WAITING_PAYMENT){
            throw new OrderStatusUpdateException("Order is not paid.");
        }else if(order.getStatus() == OrderStatus.PAID){
            throw new OrderStatusUpdateException("Order is waiting for processing.");
        }else if(order.getStatus() == OrderStatus.SHIPPED){
            throw new OrderStatusUpdateException("Order is shipped.");
        }
        order.setStatus(OrderStatus.SHIPPING);
        return repository.save(order);
    }

    public Order finishShipping(Long id){
        Order order = getOrder(id);
        if(order.getStatus() == OrderStatus.WAITING_PAYMENT){
            throw new OrderStatusUpdateException("Order is not paid.");
        }else if(order.getStatus() == OrderStatus.PAID){
            throw new OrderStatusUpdateException("Order is waiting for processing.");
        }else if(order.getStatus() == OrderStatus.PROCESSING){
            throw new OrderStatusUpdateException("Order is still processing.");
        }
        order.setStatus(OrderStatus.SHIPPED);
        return repository.save(order);
    }
}
