package com.xtsshop.app.viewmodel;

import com.xtsshop.app.db.entities.Item;
import com.xtsshop.app.service.storage.StorageService;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoProperties;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
public class ItemModel implements AbstractViewModel {
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

    public static ItemModel from(Item itemEntity, StorageService storageService){

        try{
            if(storageService == null) throw new Exception("storageService is null");
            if(itemEntity.getImage() == null) throw new Exception("item.image is null");

        }catch (Exception ex){
            ex.printStackTrace();
        }

        String imgUrl = storageService.url(itemEntity.getImage().getPath());
        return new ItemModel(
            itemEntity.getId(),
            itemEntity.getCreatedAt(),
            itemEntity.getUpdatedAt(),
            itemEntity.getName(),
            itemEntity.getPrice(),
            imgUrl,
            itemEntity.getManufacturer()
        );
    }
}