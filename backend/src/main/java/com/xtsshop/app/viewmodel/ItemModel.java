package com.xtsshop.app.viewmodel;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
public class ItemModel implements AbstractViewModel {
    private long id;
    private Date createdAt;
    private Date updatedAt;
    private String name;
    private PriceHistoryViewModel price;
    private String imgUrl;
    private String manufacturer;
    private Integer stock;
    private BrandViewModel brand;
}