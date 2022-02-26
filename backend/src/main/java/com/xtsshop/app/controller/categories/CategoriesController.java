package com.xtsshop.app.controller.categories;

import com.xtsshop.app.assembler.CategoryModelAssembler;
import com.xtsshop.app.controller.AbstractController;
import com.xtsshop.app.db.entities.Category;
import com.xtsshop.app.request.CategoryForm;
import com.xtsshop.app.service.categories.CategoriesService;
import com.xtsshop.app.viewmodel.CategoryModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.stream.Collectors;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/categories")
public class CategoriesController extends AbstractController<CategoryModel, CategoryForm, Category>{

    private final CategoriesService service;
    private final CategoryModelAssembler categoryModelAssembler;
    public CategoriesController(CategoriesService service, CategoryModelAssembler modelAssembler){
        super(service, modelAssembler);
        this.categoryModelAssembler = modelAssembler;
        this.service = service;
    }

    @Override
    public CollectionModel<EntityModel<CategoryModel>> all() {
        List<EntityModel<CategoryModel>> models = service.allRoot().stream().map(categoryModelAssembler::toModel).collect(Collectors.toList());
        return CollectionModel.of(models, linkTo(methodOn(CategoriesController.class).all()).withSelfRel());
    }
}
