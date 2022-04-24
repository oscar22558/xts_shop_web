package com.xtsshop.app.request.orders;

import com.xtsshop.app.request.users.addresses.AddressCreateRequest;
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
