package com.xtsshop.app.form.orders;

import com.xtsshop.app.form.addresses.AddressCreateForm;
import com.xtsshop.app.request.orders.OrderCreateRequest;
import com.xtsshop.app.request.orders.OrderedItemCreateRequest;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.Nullable;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;
import java.util.stream.Collectors;

@Setter
@Getter
public class OrderCreateForm {
    @NotBlank
    private String username;
    @NotNull
    private Set<@NotNull OrderedItemCreatForm> orderedItems;
    @Nullable
    @Min(0)
    private Long addressId;
    @Nullable
    private AddressCreateForm address;
    @Nullable
    private PaymentCreateForm payment;
    public OrderCreateRequest toRequest(){
        OrderCreateRequest request = new OrderCreateRequest();
        request.setOrderedItems(orderedItems.stream()
                .map(OrderedItemCreatForm::toRequest)
                .collect(Collectors.toList()));
        if(addressId != null){
            request.setAddressId(addressId);
        }
        if(address != null){
            request.setAddressCreateRequest(address.toRequest());
        }
        return request;
    }

}