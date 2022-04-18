package com.xtsshop.app.viewmodel;

import com.xtsshop.app.db.entities.Item;
import com.xtsshop.app.service.storage.StorageService;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.Optional;

@Getter
@Setter
@NoArgsConstructor
public class ItemModel implements AbstractViewModel {
    private long id;
    private Date createdAt;
    private Date updatedAt;
    private String name;
    private PriceHistoryModel price;
    private String imgUrl;
    private String manufacturer;
    private Integer stock;
}