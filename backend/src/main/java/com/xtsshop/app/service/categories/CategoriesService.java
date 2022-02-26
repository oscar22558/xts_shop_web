package com.xtsshop.app.service.categories;

import com.xtsshop.app.db.entities.Category;
import com.xtsshop.app.db.repositories.CategoryRepository;
import com.xtsshop.app.db.repositories.ItemRepository;
import com.xtsshop.app.request.CategoryForm;
import com.xtsshop.app.service.AbstractService;
import com.xtsshop.app.service.categories.items.ItemsService;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class CategoriesService extends AbstractService<CategoryForm, Category> {
    private final CategoryRepository repository;
    private final ItemsService itemsService;
    public CategoriesService(CategoryRepository repository, ItemsService itemsService){
        super(repository);
        this.itemsService = itemsService;
        this.repository = repository;
    }

    public ItemsService items(Category category){
        List<Category> list = new ArrayList<>();
        list.add(category);
        itemsService.setCategories(list);
        return itemsService;
    }

    public ItemsService items(List<Category> categories){
        itemsService.setCategories(categories);
        return itemsService;
    }

    public List<Category> allRoot(){
        return repository.getAllTopLevel();
    }


}
