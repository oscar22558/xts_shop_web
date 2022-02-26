package com.xtsshop.app.controller;

import com.xtsshop.app.assembler.AbstractModelAssembler;
import com.xtsshop.app.controller.categories.CategoriesController;
import com.xtsshop.app.db.entities.AppEntity;
import com.xtsshop.app.request.Form;
import com.xtsshop.app.service.AbstractService;
import com.xtsshop.app.viewmodel.AbstractViewModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public abstract class AbstractController<VM extends AbstractViewModel, F extends Form<E>, E extends AppEntity> {

    protected AbstractService<F, E> service;
    protected AbstractModelAssembler<VM, E> modelAssembler;

    public AbstractController(
        AbstractService<F, E> service,
        AbstractModelAssembler<VM, E> modelAssembler
    ) {
        this.service = service;
        this.modelAssembler = modelAssembler;
    }

    @GetMapping("{id}")
    public EntityModel<VM> one(@PathVariable long id){
        return modelAssembler.toModel(service.get(id));
    }
    @GetMapping()
    public CollectionModel<EntityModel<VM>> all(){
        List<E> entities = service.all();
        List<EntityModel<VM>> models = entities.stream().map(modelAssembler::toModel).collect(Collectors.toList());
        return CollectionModel.of(models, linkTo(methodOn(CategoriesController.class).all()).withSelfRel());
    }

    @PostMapping()
    public void create(@RequestBody F form){
        service.create(form);
    }

    @PutMapping("{id}")
    public void update(@PathVariable long id, @RequestBody F form){
        service.update(id, form);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable long id){
        service.delete(id);
    }
}
