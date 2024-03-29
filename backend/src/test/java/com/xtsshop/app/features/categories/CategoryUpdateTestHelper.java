package com.xtsshop.app.features.categories;

import com.xtsshop.app.db.entities.Category;
import com.xtsshop.app.db.repositories.CategoryJpaRepository;
import com.xtsshop.app.db.seed.DevDataSeed;
import org.springframework.stereotype.Component;

@Component
public class CategoryUpdateTestHelper {

    private CategoryJpaRepository repository;
    private DevDataSeed seed;

    public CategoryUpdateTestHelper(CategoryJpaRepository repository, DevDataSeed seed) {
        this.repository = repository;
        this.seed = seed;
    }

    public long getCategoryId(int index){
        return repository.findAll().get(index).getId();
    }

    public String getCategoryName(int index){
        return repository.findAll().get(index).getName();
    }

    public long getCategoryParentId(int index){
        return repository.findAll().get(index).getParent().getId();
    }

    public String getCategoryParentName(int index){
        return repository.findAll().get(index).getParent().getName();
    }

    public Category getCategoryParent(int index){
        return repository.findAll().get(index).getParent();
    }

    public void insertData(){
        seed.insertData();
    }
}
