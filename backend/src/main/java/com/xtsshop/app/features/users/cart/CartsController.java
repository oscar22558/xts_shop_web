package com.xtsshop.app.features.users.cart;

import com.xtsshop.app.features.users.cart.models.CartForm;
import com.xtsshop.app.features.users.cart.models.CartItemRepresentationModel;
import com.xtsshop.app.viewmodels.DeleteRequestViewModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users/cart")
public class CartsController {
    private CartsService cartsService;

    public CartsController(CartsService cartsService) {
        this.cartsService = cartsService;
    }

    @GetMapping
    public Collection<CartItemRepresentationModel> listItems() {
        return cartsService.listItems()
                .stream()
                .map(CartItemRepresentationModel::new)
                .collect(Collectors.toList());
    }

    @PostMapping
    public ResponseEntity<?> addItems(@Valid @RequestBody CartForm form){
        List<CartItemRepresentationModel> models = cartsService.addItems(form.toRequest())
                .stream()
                .map(CartItemRepresentationModel::new)
                .collect(Collectors.toList());
        return new ResponseEntity<>(models, HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<?> removeItems(@Valid @RequestBody CartForm form){
        cartsService.removeItems(form.toRequest());
        return new DeleteRequestViewModel().getResponse();
    }
}
