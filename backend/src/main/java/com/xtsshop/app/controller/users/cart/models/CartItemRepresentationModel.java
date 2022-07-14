package com.xtsshop.app.controller.users.cart.models;

import com.xtsshop.app.db.entities.Item;

public class CartItemRepresentationModel {
    private Item itemEntity;

    public CartItemRepresentationModel(Item item){
        this.itemEntity = item;
    }

    public long getId(){
        return itemEntity.getId();
    }

    public String getName(){
        return itemEntity.getName();
    }

    public float getPrice(){
        return itemEntity.getLatestPrice();
    }

    public String getBrand(){
        return itemEntity.getBrand().getName();
    }

    public String getManufacturer(){
        return itemEntity.getManufacturer();
    }
}
