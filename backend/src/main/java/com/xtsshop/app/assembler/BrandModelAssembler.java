package com.xtsshop.app.assembler;

import com.xtsshop.app.assembler.models.BrandRepresentationModel;
import com.xtsshop.app.controller.brands.BrandController;
import com.xtsshop.app.db.entities.Brand;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class BrandModelAssembler extends AbstractModelAssembler<BrandRepresentationModel, Brand>{
    @Override
    public EntityModel<BrandRepresentationModel> toModel(Brand entity) {
        return EntityModel.of(
                new BrandRepresentationModel(entity),
                linkTo(methodOn(BrandController.class).listAllBrands()).withRel("brands")
        );
    }
}
