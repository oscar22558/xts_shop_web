package com.xtsshop.app.features.users.payment;

import com.xtsshop.app.features.users.payment.models.CreateOrderRequest;

public interface CreateOrderService {
    void create(CreateOrderRequest request);
}
