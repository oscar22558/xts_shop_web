package com.xtsshop.app.controller.categories.items;

import com.xtsshop.app.assembler.ItemModelAssembler;
import com.xtsshop.app.db.entities.Category;
import com.xtsshop.app.db.entities.Item;
import com.xtsshop.app.db.repositories.ItemRepository;
import com.xtsshop.app.request.item.*;
import com.xtsshop.app.service.categories.CategoriesService;
import com.xtsshop.app.service.categories.items.ItemsService;
import com.xtsshop.app.viewmodel.ItemModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController("Categories.ItemsController")
@RequestMapping("/api/categories/{categoryId}")
public class ItemsController {

    private final CategoriesService service;
    private final ItemModelAssembler itemModelAssembler;
    public ItemsController(CategoriesService service, ItemModelAssembler modelAssembler){
        this.itemModelAssembler = modelAssembler;
        this.service = service;
    }

    @GetMapping("/items")
    public CollectionModel<EntityModel<ItemModel>> all(@PathVariable long categoryId){
        List<Item> items = service.items(service.get(categoryId)).all();
        List<EntityModel<ItemModel>> models = items
                .stream()
                .map(itemModelAssembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(models, linkTo(methodOn(ItemsController.class).all(categoryId)).withSelfRel());
    }


}
