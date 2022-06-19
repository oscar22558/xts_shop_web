package com.xtsshop.app.controller.categories.items;

import com.xtsshop.app.advice.exception.RecordNotFoundException;
import com.xtsshop.app.assembler.ItemModelAssembler;
import com.xtsshop.app.db.entities.Item;
import com.xtsshop.app.domain.request.categories.GetCategoryItemsRequest;
import com.xtsshop.app.domain.service.categories.items.GetCategoryItemsService;
import com.xtsshop.app.domain.service.items.CreateItemService;
import com.xtsshop.app.form.items.ItemForm;
import com.xtsshop.app.domain.service.items.QueryItemsService;
import com.xtsshop.app.viewmodel.ItemModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController("categories.items.ItemsController")
public class ItemsController {

    private ItemModelAssembler modelAssembler;
    private GetCategoryItemsService getCategoryItemsService;
    private CreateItemService createItemService;

    public ItemsController(ItemModelAssembler modelAssembler, GetCategoryItemsService getCategoryItemsService, CreateItemService createItemService) {
        this.modelAssembler = modelAssembler;
        this.getCategoryItemsService = getCategoryItemsService;
        this.createItemService = createItemService;
    }

    @GetMapping("/api/categories/items")
    public CollectionModel<EntityModel<ItemModel>> listWithPriceAndBrandFilter(
            @RequestParam(value = "categoryIds[]") Long[] categoryIds,
            @RequestParam(value = "brandIds[]", required = false) Long[] brandIds,
            @RequestParam(required = false) Float maxPrice,
            @RequestParam(required = false) Float minPrice,
            @RequestParam String sortingField,
            @RequestParam String sortingDirection
    ) throws RecordNotFoundException {
        GetCategoryItemsRequest request = new GetCategoryItemsRequest();
        List<Long> categoryIdList = Arrays.stream(categoryIds).collect(Collectors.toList());
        List<Long> brandIdList = brandIds == null ? List.of() : Arrays.stream(brandIds).collect(Collectors.toList());
        request.setCategoryId(Optional.empty());
        request.setCategoryIds(categoryIdList);
        request.setMaxPrice(Optional.ofNullable(maxPrice));
        request.setMinPrice(Optional.ofNullable(minPrice));
        request.setBrandIds(brandIdList);
        request.setSortingField(sortingField);
        request.setSortingDirection(sortingDirection);
        List<Item> items = getCategoryItemsService.getItems(request);
        return modelAssembler.toCollectionModel(items);
    }
    @GetMapping("/api/categories/{categoryId}/items")
    public CollectionModel<EntityModel<ItemModel>> listAll(
            @PathVariable Long categoryId,
            @RequestParam String sortingField,
            @RequestParam String sortingDirection
    ) throws RecordNotFoundException {
        GetCategoryItemsRequest request = new GetCategoryItemsRequest();
        request.setCategoryId(Optional.of(categoryId));
        request.setMaxPrice(Optional.empty());
        request.setMinPrice(Optional.empty());
        request.setBrandIds(new ArrayList<>());
        request.setSortingField(sortingField);
        request.setSortingDirection(sortingDirection);
        List<Item> items = getCategoryItemsService.getItems(request);

        return modelAssembler.toCollectionModel(items);
    }

    @PostMapping("/api/categories/{categoryId}/items")
    public ResponseEntity<?> create(@PathVariable long categoryId, @RequestBody ItemForm form) throws RecordNotFoundException {
        form.setCategoryId(categoryId);
        Item item = createItemService.create(form.toRequest());
        EntityModel<ItemModel> model = modelAssembler.toModel(item);
        return ResponseEntity
                .created(model.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(model);
    }
}
