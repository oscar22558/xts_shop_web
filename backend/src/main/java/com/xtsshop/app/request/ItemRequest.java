package com.xtsshop.app.request;

import com.xtsshop.app.db.entities.Category;
import com.xtsshop.app.db.entities.Item;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Nullable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;

@Getter
@Setter
@NoArgsConstructor
public class ItemRequest implements Request<Item> {
    @Nullable
    private String name;
    @Nullable
    private Float price;
    @Nullable
    private String manufacturer;
    @Nullable
    MultipartFile image;
    @Nullable
    private Long categoryId;
    @Override
    public Item toEntity() {
        long now = Calendar.getInstance().getTimeInMillis();
        Category category = new Category();
        category.setId(categoryId);
        Item item = new Item();
        item.setName(name);
        item.setCreatedAt(new Date(now));
        item.setUpdatedAt(new Date(now));
        item.setPrice(price);
        item.setManufacturer(manufacturer);
        item.setCategory(category);
        return item;
    }


    @Override
    public Item update(Item original) {
        Category category = getCategory();
        original.setName(name != null ? name : original.getName());
        original.setPrice(price != null ? price : original.getPrice());
        original.setManufacturer(manufacturer != null ? manufacturer : original.getManufacturer());
        original.setCategory(category != null ? category : original.getCategory());
        return original;
    }

    private Category getCategory(){
        if(categoryId != null){
            Category category = new Category();
            category.setId(categoryId);
            return category;
        }else{
            return null;
        }
    }
}
