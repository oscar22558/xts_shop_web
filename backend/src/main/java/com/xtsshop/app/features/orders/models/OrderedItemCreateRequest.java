package com.xtsshop.app.features.orders.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrderedItemCreateRequest {
    private Long itemId;
    private Integer quantity;
}
