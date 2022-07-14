package com.xtsshop.app.controller.categories;

import com.xtsshop.app.advices.exception.RecordNotFoundException;
import com.xtsshop.app.controller.categories.models.CategoryRequest;
import com.xtsshop.app.db.entities.Category;
import com.xtsshop.app.db.repositories.CategoryRepository;
import com.xtsshop.app.helpers.CheckedFunction;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriesService implements CheckedFunction<Long, Optional<Category>> {
    private CategoryRepository repository;
    private GetCategoriesService getCategoriesService;
    public CategoriesService(CategoryRepository repository, GetCategoriesService getCategoriesService) {
        this.repository = repository;
        this.getCategoriesService = getCategoriesService;
    }

    public Category get(Long id) throws RecordNotFoundException{
        return getCategoriesService.get(id);
    }

    public List<Category> allRoot(){
        return repository.findAllTopLevel();
    }

    public Category create(CategoryRequest request) throws RecordNotFoundException {
        Optional<Category> parent = request.getParentId().flatMap(CheckedFunction.wrap(this));
        Category entity = request.toEntity(parent);
        return repository.save(entity);
    }

    public Category update(Long id, CategoryRequest request) throws RecordNotFoundException {
        Category entity = get(id);
        Optional<Category> newParent = request.getParentId().flatMap(CheckedFunction.wrap(this));
        return repository.save(request.update(entity, newParent));
    }

    public void delete(Long id) throws RecordNotFoundException {
        repository.delete(get(id));
    }
    @Override
    public Optional<Category> apply(Long aLong) throws Exception {
        return Optional.ofNullable(get(aLong));
    }
}
