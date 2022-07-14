package com.xtsshop.app.controller.items.models;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ListItemsRequest {
    private Long[] itemIds;

    public ListItemsRequest(Long[] itemIds) {
        this.itemIds = itemIds;
    }

    public List<Long> getItemIds(){
        return itemIds == null ? List.of() : Arrays.stream(itemIds).collect(Collectors.toList());
    }
}
