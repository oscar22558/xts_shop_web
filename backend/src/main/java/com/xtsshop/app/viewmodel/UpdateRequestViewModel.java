package com.xtsshop.app.viewmodel;

import com.xtsshop.app.assembler.AbstractModelAssembler;
import com.xtsshop.app.db.entities.AppEntity;
import com.xtsshop.app.assembler.models.AbstractRepresentationModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;

public class UpdateRequestViewModel<VM extends AbstractRepresentationModel, E extends AppEntity>{

    private AbstractModelAssembler<VM, E> modelAssembler;
    private E entity;

    public UpdateRequestViewModel<VM, E> setEntity(E entity){
        this.entity = entity;
        return this;
    }

    public UpdateRequestViewModel<VM, E> setModelAssembler(AbstractModelAssembler<VM, E> modelAssembler){
        this.modelAssembler = modelAssembler;
        return this;
    }

    public ResponseEntity<EntityModel<VM>> getResponse(){
        EntityModel<VM> model = modelAssembler.toModel(entity);
        return ResponseEntity.ok(model);
    }
}
