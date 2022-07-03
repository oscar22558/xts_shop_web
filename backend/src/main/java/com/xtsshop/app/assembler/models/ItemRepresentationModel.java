package com.xtsshop.app.assembler.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
public class ItemRepresentationModel implements AbstractRepresentationModel {
    private long id;
    private Date createdAt;
    private Date updatedAt;
    private String name;
    private PriceHistoryPresentationModel price;
    private String imgUrl;
    private String manufacturer;
    private Integer stock;
    private BrandRepresentationModel brand;
}