package com.xtsshop.app.controller.categories;

import com.xtsshop.app.advice.exception.RecordNotFoundException;
import com.xtsshop.app.assembler.CategoryModelAssembler;
import com.xtsshop.app.controller.AbstractCRUDController;
import com.xtsshop.app.db.entities.Category;
import com.xtsshop.app.form.CategoryForm;
import com.xtsshop.app.request.CategoryRequest;
import com.xtsshop.app.service.categories.CategoriesService;
import com.xtsshop.app.viewmodel.CategoryModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/categories")
public class CategoriesCRUDController extends AbstractCRUDController<CategoryModel, CategoryForm, CategoryRequest, Category> {

    private final CategoriesService service;
    private final CategoryModelAssembler categoryModelAssembler;
    public CategoriesCRUDController(CategoriesService service, CategoryModelAssembler modelAssembler){
        super(service, modelAssembler);
        this.categoryModelAssembler = modelAssembler;
        this.service = service;
    }

    @Override
    @GetMapping()
    public CollectionModel<EntityModel<CategoryModel>> all() {
        List<EntityModel<CategoryModel>> models = service.allRoot().stream().map(categoryModelAssembler::toModel).collect(Collectors.toList());
        return CollectionModel.of(models, linkTo(methodOn(CategoriesCRUDController.class).all()).withSelfRel());
    }

    @Override
    @GetMapping("/{id}")
    public EntityModel<CategoryModel> one(@PathVariable Long id) throws RecordNotFoundException {
        return super.one(id);
    }

    @Override
    @PostMapping()
    public ResponseEntity<?> create(@RequestBody CategoryForm form) {
        return super.create(form);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody CategoryForm form) throws RecordNotFoundException {
        return super.update(id, form);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) throws RecordNotFoundException {
        return super.delete(id);
    }
}
