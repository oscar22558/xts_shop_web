package com.xtsshop.app.viewmodel;

import com.xtsshop.app.assembler.AbstractModelAssembler;
import com.xtsshop.app.db.entities.AppEntity;
import com.xtsshop.app.assembler.models.AbstractRepresentationModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;

import java.util.Collection;

public class CreateRequestViewModel<VM extends AbstractRepresentationModel, E extends AppEntity>{

    private AbstractModelAssembler<VM, E> modelAssembler;
    private E entity;
    private Collection<E> entities;
    private Link link;
    public CreateRequestViewModel<VM, E> setEntity(E entity){
        this.entity = entity;
        return this;
    }

    public CreateRequestViewModel<VM, E> setEntities(Collection<E> entities, Link locationLink){
        this.entities = entities;
        this.link = locationLink;
        return this;
    }
    public CreateRequestViewModel<VM, E> setModelAssembler(AbstractModelAssembler<VM, E> modelAssembler){
        this.modelAssembler = modelAssembler;
        return this;
    }

    public ResponseEntity<?> getResponse(){
        if(entity != null){
            EntityModel<VM> model = modelAssembler.toModel(entity);
            return ResponseEntity
                    .created(model.getRequiredLink(IanaLinkRelations.SELF).toUri())
                    .body(model);
        }else {

            CollectionModel<EntityModel<VM>> models = modelAssembler.toCollectionModel(entities);
            return ResponseEntity
                    .created(link.toUri())
                    .body(models);
        }
    }
}
