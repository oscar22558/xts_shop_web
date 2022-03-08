package com.xtsshop.app.controller.categories.items;

import com.xtsshop.app.assembler.ItemModelAssembler;
import com.xtsshop.app.db.entities.Item;
import com.xtsshop.app.request.ItemRequest;
import com.xtsshop.app.service.categories.CategoriesService;
import com.xtsshop.app.service.items.ItemsService;
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

@RestController("Categories.ItemsController")
@RequestMapping("/api/categories/{categoryId}/items")
public class ItemsController {

    private final CategoriesService service;
    private final ItemModelAssembler modelAssembler;
    private final ItemsService itemsService;
    public ItemsController(CategoriesService service, ItemModelAssembler modelAssembler, ItemsService itemsService){
        this.modelAssembler = modelAssembler;
        this.service = service;
        this.itemsService = itemsService;
    }

    @GetMapping()
    public CollectionModel<EntityModel<ItemModel>> all(@PathVariable long categoryId){
        List<Item> items = service.items(service.get(categoryId)).all();
        List<EntityModel<ItemModel>> models = items
                .stream()
                .map(modelAssembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(models, linkTo(methodOn(ItemsController.class).all(categoryId)).withSelfRel());
    }

    @PostMapping()
    public ResponseEntity<?> create(@PathVariable long categoryId, @RequestBody ItemRequest form){
        form.setCategoryId(categoryId);
        Item item = itemsService.create(form);
        EntityModel<ItemModel> model = modelAssembler.toModel(item);
        return ResponseEntity
                .created(model.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(model);
    }
}
