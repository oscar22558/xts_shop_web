package com.xtsshop.app.viewmodel;

import com.xtsshop.app.db.entities.Address;
import com.xtsshop.app.db.entities.Order;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;

@Getter
@NoArgsConstructor
public class OrderViewModel implements AbstractViewModel{
    private AddressViewModel address;
    private UserViewModel user;
    private String orderStatus;
    public static OrderViewModel from(Order entity){
         OrderViewModel model =new OrderViewModel();
         model.address = AddressViewModel.from(entity.getShippingAddress());
         model.user = UserViewModel.from(entity.getUser());
         model.orderStatus = entity.getStatus().name();
         return model;
    }
}
