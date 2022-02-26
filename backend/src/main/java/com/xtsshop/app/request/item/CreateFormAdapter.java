package com.xtsshop.app.request.item;

import com.xtsshop.app.db.entities.Category;
import com.xtsshop.app.db.entities.Item;

import java.sql.Date;
import java.util.Calendar;

public class CreateFormAdapter extends Item {
    private CreateForm form;
    private Date updatedAt;
    public CreateFormAdapter(CreateForm form){
        this.form = form;
        long now = Calendar.getInstance().getTimeInMillis();
        this.updatedAt = new Date(now);
    }

    public CreateFormAdapter() {
        super();
    }

    @Override
    public float getPrice() {
        return form.getPrice();
    }

    @Override
    public String getManufacturer() {
        return form.getManufacturer();
    }

    @Override
    public String getName() {
        return form.getName();
    }

    @Override
    public Date getUpdatedAt() {
        return this.updatedAt;
    }

    @Override
    public Category getCategory(){
        Category category = new Category();
        category.setId(form.getCategoryId());
        return category;
    }
}
