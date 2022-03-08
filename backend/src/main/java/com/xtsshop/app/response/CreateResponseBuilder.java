package com.xtsshop.app.response;

import com.xtsshop.app.assembler.AbstractModelAssembler;
import com.xtsshop.app.db.entities.AppEntity;
import com.xtsshop.app.viewmodel.AbstractViewModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;

public class CreateResponseBuilder<VM extends AbstractViewModel, E extends AppEntity>{

    private AbstractModelAssembler<VM, E> modelAssembler;
    private E entity;

    public CreateResponseBuilder<VM, E> setEntity(E entity){
        this.entity = entity;
        return this;
    }

    public CreateResponseBuilder<VM, E> setModelAssembler(AbstractModelAssembler<VM, E> modelAssembler){
        this.modelAssembler = modelAssembler;
        return this;
    }

    public ResponseEntity<?> build(){
        EntityModel<VM> model = modelAssembler.toModel(entity);
        return ResponseEntity
                .created(model.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(model);
    }
}
