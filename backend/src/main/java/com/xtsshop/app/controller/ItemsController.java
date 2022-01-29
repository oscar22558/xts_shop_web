package com.xtsshop.app.controller;

import com.xtsshop.app.assembler.CategoryModelAssembler;
import com.xtsshop.app.assembler.ItemModelAssembler;
import com.xtsshop.app.entities.Item;
import com.xtsshop.app.model.CategoryModel;
import com.xtsshop.app.repository.CategoryRepository;
import com.xtsshop.app.repository.ItemRepository;
import com.xtsshop.app.request.item.*;
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
@RequestMapping("/api")
public class ItemsController {

    private final ItemRepository itemRepository;
    private final ItemModelAssembler itemModelAssembler;
    public ItemsController(ItemRepository itemRepository, ItemModelAssembler modelAssembler){
        this.itemModelAssembler = modelAssembler;
        this.itemRepository = itemRepository;
    }

    @GetMapping("/item/{id}")
    public EntityModel<Item> one(@PathVariable long id){
        return itemModelAssembler.toModel(itemRepository.findById(id).orElseThrow());
    }
    @GetMapping("/items")
    public CollectionModel<EntityModel<Item>> all(){
        List<EntityModel<Item>> items = itemRepository.findAll().stream().map(itemModelAssembler::toModel).collect(Collectors.toList());
        return CollectionModel.of(items, linkTo(methodOn(ItemsController.class).all()).withSelfRel());
    }
    @PostMapping("/item")
    public ResponseEntity<EntityModel<Item>> create(@RequestBody CreateForm form){
        CreateFormAdapter adapter = new CreateFormAdapter(form);
        EntityModel<Item> model = itemModelAssembler.toModel(itemRepository.save(adapter));
        return ResponseEntity.created(model.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(model);
    }

    @PutMapping("/item")
    public ResponseEntity<EntityModel<Item>> update(@RequestBody UpdateForm form){
        Item item = itemRepository.getById(form.getId());
        UpdateFormAdapter adapter = new UpdateFormAdapter(form, item);
        EntityModel<Item> model = itemModelAssembler.toModel(itemRepository.save(adapter));
        return ResponseEntity.created(model.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(model);
    }

    @DeleteMapping("/item")
    public ResponseEntity<?> delete(@RequestBody DeleteForm form){
        DeleteFormAdapter adapter = new DeleteFormAdapter(form);
        itemRepository.save(adapter);
        return ResponseEntity.noContent().build();
    }
}
