package com.xtsshop.app.controller.items;

import com.xtsshop.app.advice.exception.RecordNotFoundException;
import com.xtsshop.app.assembler.ItemModelAssembler;
import com.xtsshop.app.db.entities.Item;
import com.xtsshop.app.form.ItemForm;
import com.xtsshop.app.request.ItemRequest;
import com.xtsshop.app.request.builder.ItemRequestBuilder;
import com.xtsshop.app.response.CreateResponseBuilder;
import com.xtsshop.app.response.DeleteResponseBuilder;
import com.xtsshop.app.response.UpdateResponseBuilder;
import com.xtsshop.app.service.images.ImagesService;
import com.xtsshop.app.service.items.ItemsService;
import com.xtsshop.app.viewmodel.ItemModel;
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

    private ItemsService service;
    private ItemModelAssembler modelAssembler;
    private ImagesService imagesService;
    public ItemsController(
        ItemsService service,
        ItemModelAssembler modelAssembler,
        ImagesService imagesService
    ){
        this.service = service;
        this.modelAssembler = modelAssembler;
        this.imagesService = imagesService;
    }

    @GetMapping("/{id}")
    public EntityModel<ItemModel> one(@PathVariable Long id) throws RecordNotFoundException {
        return modelAssembler.toModel(service.get(id));
    }

    @GetMapping()
    public CollectionModel<EntityModel<ItemModel>> all() {
        return modelAssembler.toCollectionModel(service.all());
    }

    @PostMapping()
    public ResponseEntity<?> create(
            @NotBlank @RequestParam String name,
            @NotBlank @RequestParam Float price,
            @NotBlank @RequestParam Long categoryId,
            @NotBlank @RequestParam String manufacturer,
            @NotBlank @RequestPart MultipartFile image,
            @Nullable @RequestParam Integer stack
    ) throws RecordNotFoundException {
        ItemRequest request = new ItemRequestBuilder()
                .setName(name)
                .setPrice(price)
                .setManufacturer(manufacturer)
                .setCategoryId(categoryId)
                .setImage(image)
                .setStack(stack != null ? stack : 0)
                .build();
        Item item = service.create(request);
        CreateResponseBuilder<ItemModel, Item> builder = new CreateResponseBuilder<>();
        return builder
                .setEntity(item)
                .setModelAssembler(modelAssembler)
                .build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<ItemModel>> update(@PathVariable Long id, @RequestBody ItemForm form) throws RecordNotFoundException{
        UpdateResponseBuilder<ItemModel, Item> builder = new UpdateResponseBuilder<>();
        Item entity = service.update(id, form.toRequest());
        return builder.setEntity(entity).setModelAssembler(modelAssembler).build();
    }

    @PostMapping("/{id}/image")
    public ResponseEntity<?> updateImage(
            @PathVariable Long id,
            @RequestPart MultipartFile image
    ) throws RecordNotFoundException {
        Item item = service.get(id);
        if(item == null) throw new RecordNotFoundException("Could not find item of id " + id);
        ItemRequest request = new ItemRequestBuilder()
                .setImage(image)
                .build();
        service.update(id, request);
        CreateResponseBuilder<ItemModel, Item> builder = new CreateResponseBuilder<>();
        return builder
                .setEntity(item)
                .setModelAssembler(modelAssembler)
                .build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) throws RecordNotFoundException {
        service.delete(id);
        return new DeleteResponseBuilder().build();
    }
}
