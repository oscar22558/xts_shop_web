package com.xtsshop.app.controller.items;

import com.xtsshop.app.advice.exception.RecordNotFoundException;
import com.xtsshop.app.assembler.ItemModelAssembler;
import com.xtsshop.app.controller.AbstractCRUDController;
import com.xtsshop.app.db.entities.Item;
import com.xtsshop.app.form.ItemForm;
import com.xtsshop.app.request.ImageRequest;
import com.xtsshop.app.request.ItemRequest;
import com.xtsshop.app.response.CreateResponseBuilder;
import com.xtsshop.app.response.UpdateResponseBuilder;
import com.xtsshop.app.service.images.ImagesService;
import com.xtsshop.app.service.items.ItemsService;
import com.xtsshop.app.viewmodel.ItemModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/items")
public class ItemsController extends AbstractCRUDController<ItemModel, ItemForm, ItemRequest, Item> {

    private ItemsService service;
    private ItemModelAssembler modelAssembler;
    private ImagesService imagesService;
    public ItemsController(
        ItemsService service,
        ItemModelAssembler modelAssembler,
        ImagesService imagesService
    ){
        super(service, modelAssembler);
        this.service = service;
        this.modelAssembler = modelAssembler;
        this.imagesService = imagesService;
    }

    @Override
    @GetMapping("/{id}")
    public EntityModel<ItemModel> one(@PathVariable Long id) throws RecordNotFoundException {
        return super.one(id);
    }

    @Override
    @GetMapping()
    public CollectionModel<EntityModel<ItemModel>> all() {
        return super.all();
    }

    @PostMapping()
    public ResponseEntity<?> create(
            @RequestParam String name,
            @RequestParam Float price,
            @RequestParam String manufacturer,
            @RequestParam Long categoryId,
            @RequestPart MultipartFile image
    ) {
        ItemRequest request = new ItemRequest();
        request.setName(name);
        request.setPrice(price);
        request.setManufacturer(manufacturer);
        request.setCategoryId(categoryId);
        request.setImage(image);
        Item item = service.create(request);
        CreateResponseBuilder<ItemModel, Item> builder = new CreateResponseBuilder<>();
        return builder
                .setEntity(item)
                .setModelAssembler(modelAssembler)
                .build();
    }


    @PutMapping("/{id}")
    @Override
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody ItemForm form) throws RecordNotFoundException{
        return super.update(id, form);
    }

    @PostMapping("/{id}/image")
    public ResponseEntity<?> updateImage(
            @PathVariable Long id,
            @RequestPart MultipartFile image
    ) throws RecordNotFoundException {
        Item item = service.get(id);
        if(item == null) throw new RecordNotFoundException("Could not find item of id " + id);
        ItemRequest request = new ItemRequest();
        request.setImage(image);
        service.update(id, request);
        CreateResponseBuilder<ItemModel, Item> builder = new CreateResponseBuilder<>();
        return builder
                .setEntity(item)
                .setModelAssembler(modelAssembler)
                .build();
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) throws RecordNotFoundException {
        return super.delete(id);
    }
}
