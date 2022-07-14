package com.xtsshop.app.controller.users.cart.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CartRequest {
    private List<Long> itemIds;
}
