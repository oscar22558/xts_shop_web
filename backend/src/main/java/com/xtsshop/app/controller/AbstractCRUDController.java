package com.xtsshop.app.controller;

import com.xtsshop.app.advice.exception.RecordNotFoundException;
import com.xtsshop.app.assembler.AbstractModelAssembler;
import com.xtsshop.app.controller.categories.CategoriesCRUDController;
import com.xtsshop.app.db.entities.AppEntity;
import com.xtsshop.app.form.Form;
import com.xtsshop.app.request.Request;
import com.xtsshop.app.service.AbstractService;
import com.xtsshop.app.viewmodel.AbstractViewModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public abstract class AbstractCRUDController<VM extends AbstractViewModel, F extends Form<R, E>, R extends Request<E>, E extends AppEntity> {

    protected AbstractService<R, E> service;
    protected AbstractModelAssembler<VM, E> modelAssembler;

    public AbstractCRUDController(
        AbstractService<R, E> service,
        AbstractModelAssembler<VM, E> modelAssembler
    ) {
        this.service = service;
        this.modelAssembler = modelAssembler;
    }

    public EntityModel<VM> one(Long id) throws RecordNotFoundException {
        E entity = service.get(id);
        if(entity == null) throw new RecordNotFoundException("Record of id " + id + "not found.");
        return modelAssembler.toModel(service.get(id));
    }

    public CollectionModel<EntityModel<VM>> all(){
        List<E> entities = service.all();
        List<EntityModel<VM>> models = entities.stream().map(modelAssembler::toModel).collect(Collectors.toList());
        return CollectionModel.of(models, linkTo(methodOn(CategoriesCRUDController.class).all()).withSelfRel());
    }

    public ResponseEntity<?> create(F form){
        R request = form.toRequest();
        EntityModel<VM> model = modelAssembler.toModel(service.create(request));
        return ResponseEntity
                .created(model.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(model);
    }

    public ResponseEntity<?> update(Long id, F form) throws RecordNotFoundException{
        E entity = service.get(id);
        if(entity == null) throw new RecordNotFoundException("Record of id " + id + "not found.");
        EntityModel<VM> model = modelAssembler.toModel(service.update(id, form.toRequest()));
        return ResponseEntity.ok(model);
    }

    public ResponseEntity<?> delete(Long id) throws RecordNotFoundException{
        E entity = service.get(id);
        if(entity == null) throw new RecordNotFoundException("Record of id " + id + "not found.");
        service.delete(id);
        return ResponseEntity.ok().build();
    }
}
