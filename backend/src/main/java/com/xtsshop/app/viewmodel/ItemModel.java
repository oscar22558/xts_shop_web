package com.xtsshop.app.viewmodel;

import com.xtsshop.app.db.entities.Item;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
public class ItemModel {
    private long id;
    private Date created_at;
    private Date updated_at;
    private String name;
    private float price;
    private String imgUrl;
    private String manufacturer;

    public ItemModel(long id, Date created_at, Date updated_at, String name, float price, String imgUrl, String manufacturer) {
        this.id = id;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.name = name;
        this.price = price;
        this.imgUrl = imgUrl;
        this.manufacturer = manufacturer;
    }

    public static ItemModel from(Item itemEntity){
        return new ItemModel(
            itemEntity.getId(),
            itemEntity.getCreatedAt(),
            itemEntity.getUpdatedAt(),
            itemEntity.getName(),
            itemEntity.getPrice(),
            itemEntity.getImgUrl(),
            itemEntity.getManufacturer()
        );
    }
}