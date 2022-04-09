package com.xtsshop.app.form.orders;

import com.xtsshop.app.form.addresses.AddressCreateForm;
import com.xtsshop.app.request.orders.OrderCreateRequest;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Setter
@Getter
public class OrderCreateForm {
    @Nullable
    private String username;
    @NotNull
    private Set<@NotNull Long> priceHistoryIds;
    @Nullable
    private Long addressId;
    @Nullable
    private AddressCreateForm address;
    @Nullable
    private Long paymentId;
    public OrderCreateRequest toRequest(){
        OrderCreateRequest request = new OrderCreateRequest();
        request.setPaymentId(paymentId);
        request.setPriceHistoryIds(priceHistoryIds);
        if(addressId != null){
            request.setAddressId(addressId);
        }
        if(address != null){
            request.setAddressCreateRequest(address.toRequest());
        }
        return request;
    }

}