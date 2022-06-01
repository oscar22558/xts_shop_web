package com.xtsshop.app.controller.categories.items;

import com.xtsshop.app.advice.exception.RecordNotFoundException;
import com.xtsshop.app.assembler.ItemModelAssembler;
import com.xtsshop.app.db.entities.Item;
import com.xtsshop.app.domain.request.categories.GetCategoryItemsRequest;
import com.xtsshop.app.domain.service.categories.items.GetCategoryItemsService;
import com.xtsshop.app.form.ItemForm;
import com.xtsshop.app.domain.service.items.ItemsService;
import com.xtsshop.app.viewmodel.ItemModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController("categories.items.ItemsController")
public class ItemsController {

    private final ItemModelAssembler modelAssembler;
    private final ItemsService itemsService;
    private GetCategoryItemsService getCategoryItemsService;
    public ItemsController(ItemModelAssembler modelAssembler, ItemsService itemsService, GetCategoryItemsService getCategoryItemsService){
        this.modelAssembler = modelAssembler;
        this.itemsService = itemsService;
        this.getCategoryItemsService = getCategoryItemsService;
    }

    @GetMapping()
    @RequestMapping("/api/categories/items")
    public CollectionModel<EntityModel<ItemModel>> list(
            @RequestParam Long[] categoryIds,
            @RequestParam Float minPrice,
            @RequestParam Float maxPrice,
            @RequestParam Long[] brandIds
    ) throws RecordNotFoundException {
        GetCategoryItemsRequest request = new GetCategoryItemsRequest();
        List<Long> categoryIdList = Arrays.stream(categoryIds).collect(Collectors.toList());
        List<Long> brandIdList = Arrays.stream(brandIds).collect(Collectors.toList());
        request.setCategoryIds(categoryIdList);
        request.setMaxPrice(Optional.of(maxPrice));
        request.setMinPrice(Optional.of(minPrice));
        request.setBrandIds(brandIdList);
        List<Item> items = getCategoryItemsService.getItems(request);
        return modelAssembler.toCollectionModel(items);
    }
    @GetMapping()
    @RequestMapping("/api/categories/{categoryId}/items")
    public CollectionModel<EntityModel<ItemModel>> listAll(
            @PathVariable Long categoryId
    ) throws RecordNotFoundException {
        GetCategoryItemsRequest request = new GetCategoryItemsRequest();
        request.setCategoryId(Optional.of(categoryId));
        List<Item> items = getCategoryItemsService.getItems(request);
        return modelAssembler.toCollectionModel(items);
    }

    @PostMapping()
    @RequestMapping("/api/categories/{categoryId}/items")
    public ResponseEntity<?> create(@PathVariable long categoryId, @RequestBody ItemForm form) throws RecordNotFoundException {
        form.setCategoryId(categoryId);
        Item item = itemsService.create(form.toRequest());
        EntityModel<ItemModel> model = modelAssembler.toModel(item);
        return ResponseEntity
                .created(model.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(model);
    }
}
