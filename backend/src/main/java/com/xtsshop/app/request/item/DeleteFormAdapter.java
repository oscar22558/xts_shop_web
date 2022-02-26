package com.xtsshop.app.request.item;

import com.xtsshop.app.db.entities.Item;

public class DeleteFormAdapter extends Item {
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
