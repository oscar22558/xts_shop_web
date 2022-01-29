package com.xtsshop.app.request.item;

import com.xtsshop.app.entities.Category;
import com.xtsshop.app.entities.Item;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.annotation.Nullable;
import java.sql.Date;
import java.util.Calendar;

public class UpdateFormAdapter extends Item {
    private UpdateForm form;
    private Item originalItem;
    private Date updatedAt;
    public UpdateFormAdapter(UpdateForm form, Item originalItem){
        this.form = form;
        this.originalItem = originalItem;
        Long now = Calendar.getInstance().getTimeInMillis();
        this.updatedAt = new Date(now);
    }

    public UpdateFormAdapter() { super(); }

    @Override
    public float getPrice() {
        return form.getPrice() == null ? originalItem.getPrice() : form.getPrice();
    }

    @Override
    public String getManufacturer() {
        return form.getManufacturer() == null ? originalItem.getManufacturer() : form.getManufacturer();
    }

    @Override
    public String getName() {
        return form.getName() == null ? originalItem.getName() : form.getName();
    }

    @Override
    public Date getUpdatedAt() {
        return this.updatedAt;
    }

    @Override
    public Category getCategory(){
        if(form.getCategoryId() == null){
           return originalItem.getCategory();
        }else{
            Category category = new Category();
            category.setId(form.getCategoryId());
            return category;
        }
    }
}
