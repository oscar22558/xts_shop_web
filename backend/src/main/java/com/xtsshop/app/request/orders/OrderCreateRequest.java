package com.xtsshop.app.request.orders;

import com.xtsshop.app.db.entities.Address;
import com.xtsshop.app.db.entities.AppUser;
import com.xtsshop.app.db.entities.Order;
import com.xtsshop.app.request.users.addresses.AddressCreateRequest;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.Nullable;
import java.util.Set;

@Getter
@Setter
public class OrderCreateRequest {
    private Set<Long> priceHistoryIds;
    @Nullable
    private Long addressId;
    @Nullable
    private AddressCreateRequest addressCreateRequest;
    @Nullable
    private String username;
    private Long paymentId;
}
