package com.xtsshop.app.viewmodel;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.EntityModel;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class OrderModel implements AbstractViewModel{
    private AddressViewModel address;
    private UserViewModel user;
    private String orderStatus;
    private Collection<OrderedItemModel> items;
}
