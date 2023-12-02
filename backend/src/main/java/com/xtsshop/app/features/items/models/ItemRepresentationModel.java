package com.xtsshop.app.features.items.models;

import com.xtsshop.app.AbstractRepresentationModel;
import com.xtsshop.app.db.entities.Item;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.Optional;

public class ItemRepresentationModel implements AbstractRepresentationModel {
    private Item item;

    public ItemRepresentationModel(Item item){
       this.item = item;
    }

    public long getId(){
        return item.getId();
    }
    public String getDescription(){
        return item.getDescription();
    }

    public String getName(){
        return item.getName();
    }
    public float getPrice(){
        return item.getLatestPrice();
    }
    public String getImgUrl(){
        return item.getImage().getUri();
    };
    public String getManufacturer(){
        return item.getManufacturer();
    };
    public String getBrand(){
        return item.getBrand().getName();
    }

}