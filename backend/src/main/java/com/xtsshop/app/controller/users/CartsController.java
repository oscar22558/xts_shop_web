package com.xtsshop.app.controller.users;

import com.xtsshop.app.assembler.ItemModelAssembler;
import com.xtsshop.app.db.entities.Item;
import com.xtsshop.app.form.CartForm;
import com.xtsshop.app.response.CreateResponseBuilder;
import com.xtsshop.app.response.DeleteResponseBuilder;
import com.xtsshop.app.domain.service.users.carts.CartsService;
import com.xtsshop.app.viewmodel.ItemModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/users/cart")
public class CartsController {
    private CartsService cartsService;
    private ItemModelAssembler itemModelAssembler;
    public CartsController(CartsService cartsService, ItemModelAssembler itemModelAssembler) {
        this.cartsService = cartsService;
        this.itemModelAssembler = itemModelAssembler;
    }

    @GetMapping
    public CollectionModel<EntityModel<ItemModel>> listItems() {
        Link link = linkTo(methodOn(CartsController.class).listItems()).withSelfRel();
        return itemModelAssembler.toCollectionModel(cartsService.listItems()).add(link);
    }
    @PostMapping
    public ResponseEntity<?> addItems(@Valid @RequestBody CartForm form){
        Link link = linkTo(methodOn(CartsController.class).listItems()).withSelfRel();
        List<Item> models = cartsService.addItems(form.toRequest());
        return new CreateResponseBuilder<ItemModel, Item>()
                .setModelAssembler(itemModelAssembler)
                .setEntities(models, link)
                .build();
    }
    @DeleteMapping
    public ResponseEntity<?> removeItems(@Valid @RequestBody CartForm form){
        cartsService.removeItems(form.toRequest());
        return new DeleteResponseBuilder().build();
    }
}
