package com.xtsshop.app.controller.items;

import com.xtsshop.app.advice.exception.RecordNotFoundException;
import com.xtsshop.app.assembler.ItemModelAssembler;
import com.xtsshop.app.db.entities.Item;
import com.xtsshop.app.domain.request.items.ListItemsRequest;
import com.xtsshop.app.domain.request.items.images.UpdateItemImageRequest;
import com.xtsshop.app.domain.service.items.*;
import com.xtsshop.app.domain.request.items.CreateItemRequest;
import com.xtsshop.app.domain.request.items.CreateItemRequestBuilder;
import com.xtsshop.app.form.items.UpdateItemForm;
import com.xtsshop.app.viewmodel.CreateRequestViewModel;
import com.xtsshop.app.viewmodel.DeleteRequestViewModel;
import com.xtsshop.app.viewmodel.UpdateRequestViewModel;
import com.xtsshop.app.domain.service.items.images.ImagesService;
import com.xtsshop.app.assembler.models.ItemRepresentationModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Nullable;
import javax.validation.constraints.NotBlank;

@RestController
@RequestMapping("/api/items")
public class ItemsController{

    private QueryItemsService service;
    private ItemModelAssembler modelAssembler;
    private ImagesService imagesService;
    private CreateItemService createItemService;
    private UpdateItemService updateItemService;
    private DeleteItemService deleteItemService;
    private UpdateItemImageService updateItemImageService;

    public ItemsController(QueryItemsService service, ItemModelAssembler modelAssembler, ImagesService imagesService, CreateItemService createItemService, UpdateItemService updateItemService, DeleteItemService deleteItemService, UpdateItemImageService updateItemImageService) {
        this.service = service;
        this.modelAssembler = modelAssembler;
        this.imagesService = imagesService;
        this.createItemService = createItemService;
        this.updateItemService = updateItemService;
        this.deleteItemService = deleteItemService;
        this.updateItemImageService = updateItemImageService;
    }

    @GetMapping("/{id}")
    public EntityModel<ItemRepresentationModel> one(@PathVariable Long id) throws RecordNotFoundException {
        return modelAssembler.toModel(service.get(id));
    }

    @GetMapping()
    public CollectionModel<EntityModel<ItemRepresentationModel>> all(
            @RequestParam(name = "ids", required = false) Long[] itemIds
    ) {
        ListItemsRequest request = new ListItemsRequest(itemIds);
        return modelAssembler.toCollectionModel(service.listItems(request));
    }

    @PostMapping()
    public ResponseEntity<?> create(
            @NotBlank @RequestParam String name,
            @NotBlank @RequestParam Float price,
            @NotBlank @RequestParam Long categoryId,
            @NotBlank @RequestParam String manufacturer,
            @NotBlank @RequestPart MultipartFile image,
            @Nullable @RequestParam Integer stack,
            @NotBlank @RequestParam Long brandId
    ) {
        CreateItemRequest request = new CreateItemRequestBuilder()
                .setName(name)
                .setPrice(price)
                .setManufacturer(manufacturer)
                .setCategoryId(categoryId)
                .setImage(image)
                .setStack(stack != null ? stack : 0)
                .setBrandId(brandId)
                .build();
        Item item = createItemService.create(request);
        return new CreateRequestViewModel<ItemRepresentationModel, Item>()
                .setEntity(item)
                .setModelAssembler(modelAssembler)
                .getResponse();
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<ItemRepresentationModel>> update(@PathVariable Long id, @RequestBody UpdateItemForm form) {
        Item entity = updateItemService.update(form.toRequest(id));
        return new UpdateRequestViewModel<ItemRepresentationModel, Item>()
                .setEntity(entity)
                .setModelAssembler(modelAssembler)
                .getResponse();
    }

    @PostMapping("/{id}/image")
    public ResponseEntity<?> updateImage(
            @PathVariable Long id,
            @RequestPart MultipartFile image
    ) throws RecordNotFoundException {
        Item item = updateItemImageService.update(new UpdateItemImageRequest(id, image));
        return new CreateRequestViewModel<ItemRepresentationModel, Item>()
                .setEntity(item)
                .setModelAssembler(modelAssembler)
                .getResponse();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) throws RecordNotFoundException {
        deleteItemService.delete(id);
        return new DeleteRequestViewModel().getResponse();
    }
}
