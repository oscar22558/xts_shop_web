package com.xtsshop.app.controller.categories;

import com.xtsshop.app.advice.exception.RecordNotFoundException;
import com.xtsshop.app.assembler.CategoryModelAssembler;
import com.xtsshop.app.db.entities.Category;
import com.xtsshop.app.form.CategoryForm;
import com.xtsshop.app.domain.request.categories.CategoryRequest;
import com.xtsshop.app.viewmodel.CreateRequestViewModel;
import com.xtsshop.app.viewmodel.DeleteRequestViewModel;
import com.xtsshop.app.viewmodel.UpdateRequestViewModel;
import com.xtsshop.app.domain.service.categories.CategoriesService;
import com.xtsshop.app.assembler.models.CategoryRepresentationModel;
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
    public CollectionModel<EntityModel<CategoryRepresentationModel>> all() {
        return modelAssembler.toCollectionModel(service.allRoot());
    }

    @GetMapping("/{id}")
    public EntityModel<CategoryRepresentationModel> one(@PathVariable Long id) throws RecordNotFoundException {
        return modelAssembler.toModel(service.get(id));
    }

    @PostMapping()
    public ResponseEntity<?> create(@RequestBody CategoryForm form) throws RecordNotFoundException{
        CategoryRequest request = form.toRequest();
        Category entity = service.create(request);
        return new CreateRequestViewModel<CategoryRepresentationModel, Category>().setEntity(entity).setModelAssembler(modelAssembler).getResponse();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody CategoryForm form) throws RecordNotFoundException {
        Category entity = service.get(id);
        service.update(id, form.toRequest());
        return new UpdateRequestViewModel<CategoryRepresentationModel, Category>().setEntity(entity).setModelAssembler(modelAssembler).getResponse();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) throws RecordNotFoundException {
        service.delete(id);
        return new DeleteRequestViewModel().getResponse();
    }
}
