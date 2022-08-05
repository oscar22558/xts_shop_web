package com.xtsshop.app.features.orders.models;

import com.xtsshop.app.features.users.addresses.models.AddressCreateForm;
import com.xtsshop.app.features.users.cart.models.OrderedItemCreateForm;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.annotation.Nullable;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

@Setter
@Getter
@NoArgsConstructor
public class UserOrderCreateForm {
    @NotNull
    @NotEmpty
    private List<@NotNull OrderedItemCreateForm> orderedItems;
    @Nullable
    @Min(0)
    private Long addressId;
    @Nullable
    private AddressCreateForm address;

    public OrderCreateRequest toRequest(){
        OrderCreateRequest request = new OrderCreateRequest();
        request.setOrderedItems(orderedItems.stream()
                .map(OrderedItemCreateForm::toRequest)
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