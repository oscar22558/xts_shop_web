package com.xtsshop.app.request.category;

import com.xtsshop.app.entities.Category;
import com.xtsshop.app.entities.Item;

import java.sql.Date;
import java.util.Calendar;

public class DeleteFormAdapter extends Category {
    private DeleteForm form;
    public DeleteFormAdapter(DeleteForm form){
        this.form = form;
    }

    public DeleteFormAdapter() {
        super();
    }

    @Override
    public long getId() {
        return form.getId();
    }
}
