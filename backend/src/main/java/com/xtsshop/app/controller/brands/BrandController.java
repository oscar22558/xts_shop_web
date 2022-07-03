package com.xtsshop.app.controller.brands;

import com.xtsshop.app.assembler.BrandModelAssembler;
import com.xtsshop.app.assembler.models.BrandRepresentationModel;
import com.xtsshop.app.db.entities.Brand;
import com.xtsshop.app.domain.request.brands.BrandCreateRequest;
import com.xtsshop.app.domain.service.brands.BrandService;
import com.xtsshop.app.viewmodel.CreateRequestViewModel;
import com.xtsshop.app.viewmodel.DeleteRequestViewModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/brands")
public class BrandController {
    private BrandService brandService;
    private BrandModelAssembler brandModelAssembler;

    public BrandController(BrandService brandService, BrandModelAssembler brandModelAssembler) {
        this.brandService = brandService;
        this.brandModelAssembler = brandModelAssembler;
    }

    @GetMapping
    public CollectionModel<EntityModel<BrandRepresentationModel>> listAllBrands(){
        return toEntityModels(brandService.listAllBrands());
    }

    @PostMapping
    public ResponseEntity<?> create(
            @RequestBody String name
    ){
        BrandCreateRequest request = new BrandCreateRequest(name);
        Brand brand = brandService.create(request);
        return new CreateRequestViewModel<BrandRepresentationModel, Brand>()
                .setEntity(brand)
                .getResponse();
    }

    @DeleteMapping
    public ResponseEntity<?> delete(
            @RequestBody Long id
    ){
        brandService.delete(id);
        return new DeleteRequestViewModel().getResponse();
    }

    private CollectionModel<EntityModel<BrandRepresentationModel>> toEntityModels(List<Brand> brands){
        return brandModelAssembler.toCollectionModel(brands);
    }
}
