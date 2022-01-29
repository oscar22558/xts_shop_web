package com.xtsshop.app.request.category;

import com.xtsshop.app.entities.Category;
import com.xtsshop.app.entities.Item;

import java.sql.Date;
import java.util.Calendar;

public class UpdateFormAdapter extends Category {
    private UpdateForm form;
    private Category originalCategory;
    private Date updatedAt;
    public UpdateFormAdapter(UpdateForm form, Category originalCategory){
        this.form = form;
        this.originalCategory = originalCategory;
        long now = Calendar.getInstance().getTimeInMillis();
        this.updatedAt = new Date(now);
    }

    public UpdateFormAdapter() { super(); }

    @Override
    public String getName() {
        return form.getName() == null ? originalCategory.getName() : form.getName();
    }

    @Override
    public Date getUpdatedAt() {
        return this.updatedAt;
    }

    @Override
    public Category getParent() {
        if(form.getParentId() == null){
            return originalCategory.getParent();
        }else{
            Category newParent = new Category();
            newParent.setId(form.getParentId());
            return newParent;
        }
    }
}
