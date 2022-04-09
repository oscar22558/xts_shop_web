package com.xtsshop.app.request.users.cart;

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
