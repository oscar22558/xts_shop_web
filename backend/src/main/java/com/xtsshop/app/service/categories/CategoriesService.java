package com.xtsshop.app.service.categories;

import com.xtsshop.app.advice.exception.RecordNotFoundException;
import com.xtsshop.app.db.entities.Category;
import com.xtsshop.app.db.entities.Item;
import com.xtsshop.app.db.repositories.CategoryRepository;
import com.xtsshop.app.request.CategoryRequest;
import com.xtsshop.app.service.items.ItemsService;
import com.xtsshop.app.util.CheckedFunction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoriesService implements CheckedFunction<Long, Optional<Category>> {
    private CategoryRepository repository;
    private ItemsService itemsService;
    private CategoriesGetService categoriesGetService;
    public CategoriesService(CategoryRepository repository, ItemsService itemsService, CategoriesGetService categoriesGetService) {
        this.repository = repository;
        this.itemsService = itemsService;
        this.categoriesGetService = categoriesGetService;
    }

    public Category get(Long id) throws RecordNotFoundException{
        return categoriesGetService.get(id);
    }
    public List<Item> items(Category category){
        List<Long> categoryId = new ArrayList<>();
        categoryId.add(category.getId());
        return itemsService.findItemsByCategoryId(categoryId);
    }

    public List<Item> items(List<Category> categories){
        return itemsService.findItemsByCategoryId(categories.stream().map(Category::getId).collect(Collectors.toList()));
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
