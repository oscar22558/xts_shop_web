package com.xtsshop.app.request.category;

import com.xtsshop.app.entities.Category;

import java.sql.Date;
import java.util.Calendar;

public class CreateFormAdapter extends Category {
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
    public String getName() {
        return form.getName();
    }

    @Override
    public Date getUpdatedAt() {
        return this.updatedAt;
    }

    @Override
    public Category getParent() {
        Category parent = new Category();
        parent.setId(form.getParentId());
        return parent;
    }
}
