package com.xtsshop.app.features.orders.models;

import com.xtsshop.app.features.users.addresses.models.AddressCreateRequest;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.Nullable;
import java.util.List;

@Getter
@Setter
public class OrderCreateRequest {
    @Nullable
    private String username;
    private List<OrderedItemCreateRequest> orderedItems;
    @Nullable
    private Long addressId;
    @Nullable
    private AddressCreateRequest addressCreateRequest;
}
