package com.xtsshop.app.controller;

import com.xtsshop.app.assembler.ItemModelAssembler;
import com.xtsshop.app.db.entities.Item;
import com.xtsshop.app.db.repositories.ItemRepository;
import com.xtsshop.app.request.item.*;
import com.xtsshop.app.viewmodel.ItemModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/items")
public class ItemsController {

    private final ItemRepository itemRepository;
    private final ItemModelAssembler itemModelAssembler;
    public ItemsController(ItemRepository itemRepository, ItemModelAssembler modelAssembler){
        this.itemModelAssembler = modelAssembler;
        this.itemRepository = itemRepository;
    }

    @GetMapping("/{id}")
    public EntityModel<ItemModel> one(@PathVariable long id){
        return itemModelAssembler.toModel(itemRepository.findById(id).orElseThrow());
    }
    @GetMapping()
    public CollectionModel<EntityModel<ItemModel>> all(){
        List<EntityModel<ItemModel>> items = itemRepository.findAll().stream().map(itemModelAssembler::toModel).collect(Collectors.toList());
        return CollectionModel.of(items, linkTo(methodOn(ItemsController.class).all()).withSelfRel());
    }
    @PostMapping()
    public ResponseEntity<EntityModel<ItemModel>> create(@RequestBody CreateForm form){
        CreateFormAdapter adapter = new CreateFormAdapter(form);
        EntityModel<ItemModel> model = itemModelAssembler.toModel(itemRepository.save(adapter));
        return ResponseEntity.created(model.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(model);
    }

    @PutMapping()
    public ResponseEntity<EntityModel<ItemModel>> update(@RequestBody UpdateForm form){
        Item item = itemRepository.getById(form.getId());
        UpdateFormAdapter adapter = new UpdateFormAdapter(form, item);
        EntityModel<ItemModel> model = itemModelAssembler.toModel(itemRepository.save(adapter));
        return ResponseEntity.created(model.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(model);
    }

    @DeleteMapping()
    public ResponseEntity<?> delete(@RequestBody DeleteForm form){
        DeleteFormAdapter adapter = new DeleteFormAdapter(form);
        itemRepository.save(adapter);
        return ResponseEntity.noContent().build();
    }
}
