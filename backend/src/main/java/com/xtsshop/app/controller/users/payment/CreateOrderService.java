package com.xtsshop.app.controller.users.payment;

import com.xtsshop.app.controller.users.payment.models.CreateOrderRequest;

public interface CreateOrderService {
    void create(CreateOrderRequest request);
}
