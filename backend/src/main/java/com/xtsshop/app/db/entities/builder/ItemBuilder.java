package com.xtsshop.app.db.entities.builder;

import com.xtsshop.app.db.entities.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
public class ItemBuilder {
    private String name;
    private String description;
    private float price;
    private String manufacturer;
    private int stock;
    private Image image;
    private List<PriceHistory> priceHistories;
    private Category category;
    private Brand brand;
    public ItemBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public ItemBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    public ItemBuilder setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
        return this;
    }

    public ItemBuilder setStock(int stock) {
        this.stock = stock;
        return this;
    }

    public ItemBuilder setImage(Image image) {
        this.image = image;
        return this;
    }

    public ItemBuilder setCategory(Category category) {
        this.category = category;
        return this;
    }

    public ItemBuilder setBrand(Brand brand) {
        this.brand = brand;
        return this;
    }

    public ItemBuilder setPrice(float price) {
        this.price = price;
        return this;
    }

    public Item build(){
        Date now = new Date(Calendar.getInstance().getTimeInMillis());
        Item item = new Item();
        item.setCreatedAt(now);
        item.setUpdatedAt(now);
        item.setName(name);
        item.setDescription(description);
        item.setPrice(price);
        item.setManufacturer(manufacturer);
        item.setStock(stock);
        item.setImage(image);

        if(priceHistories == null) priceHistories = new ArrayList<>();
        priceHistories.forEach(history->history.setItem(item));
        item.setPriceHistories(priceHistories);

        item.setCategory(category);
        item.setBrand(brand);
        return item;
    }
}
