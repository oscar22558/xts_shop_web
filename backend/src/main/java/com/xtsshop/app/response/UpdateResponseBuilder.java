package com.xtsshop.app.response;

import com.xtsshop.app.assembler.AbstractModelAssembler;
import com.xtsshop.app.db.entities.AppEntity;
import com.xtsshop.app.viewmodel.AbstractViewModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;

public class UpdateResponseBuilder<VM extends AbstractViewModel, E extends AppEntity>{

    private AbstractModelAssembler<VM, E> modelAssembler;
    private E entity;

    public UpdateResponseBuilder<VM, E> setEntity(E entity){
        this.entity = entity;
        return this;
    }

    public UpdateResponseBuilder<VM, E> setModelAssembler(AbstractModelAssembler<VM, E> modelAssembler){
        this.modelAssembler = modelAssembler;
        return this;
    }

    public ResponseEntity<?> build(){
        EntityModel<VM> model = modelAssembler.toModel(entity);
        return ResponseEntity.ok(model);
    }
}
