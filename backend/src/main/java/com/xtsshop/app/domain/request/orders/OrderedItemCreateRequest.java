package com.xtsshop.app.domain.request.orders;

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
