package com.xtsshop.app.controller.categories;

import com.xtsshop.app.advice.exception.RecordNotFoundException;
import com.xtsshop.app.assembler.CategoryModelAssembler;
import com.xtsshop.app.db.entities.Category;
import com.xtsshop.app.form.CategoryForm;
import com.xtsshop.app.domain.request.categories.CategoryRequest;
import com.xtsshop.app.response.CreateResponseBuilder;
import com.xtsshop.app.response.DeleteResponseBuilder;
import com.xtsshop.app.response.UpdateResponseBuilder;
import com.xtsshop.app.domain.service.categories.CategoriesService;
import com.xtsshop.app.viewmodel.CategoryModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping("/api/categories")
public class CategoriesController {

    private CategoriesService service;
    private CategoryModelAssembler modelAssembler;
    public CategoriesController(CategoriesService service, CategoryModelAssembler modelAssembler){
        this.modelAssembler = modelAssembler;
        this.service = service;
    }

    @GetMapping()
    public CollectionModel<EntityModel<CategoryModel>> all() {
        return modelAssembler.toCollectionModel(service.allRoot());
    }

    @GetMapping("/{id}")
    public EntityModel<CategoryModel> one(@PathVariable Long id) throws RecordNotFoundException {
        return modelAssembler.toModel(service.get(id));
    }

    @PostMapping()
    public ResponseEntity<?> create(@RequestBody CategoryForm form) throws RecordNotFoundException{
        CategoryRequest request = form.toRequest();
        Category entity = service.create(request);
        return new CreateResponseBuilder<CategoryModel, Category>().setEntity(entity).setModelAssembler(modelAssembler).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody CategoryForm form) throws RecordNotFoundException {
        Category entity = service.get(id);
        service.update(id, form.toRequest());
        return new UpdateResponseBuilder<CategoryModel, Category>().setEntity(entity).setModelAssembler(modelAssembler).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) throws RecordNotFoundException {
        service.delete(id);
        return new DeleteResponseBuilder().build();
    }
}
