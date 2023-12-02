package com.xtsshop.app.features.categories;

import com.xtsshop.app.db.repositories.CategoryJpaRepository;
import com.xtsshop.app.db.seed.DevDataSeed;
import org.springframework.stereotype.Component;

@Component
public class DeleteTestHelper {
    private String route = "/api/categories/{id}";
    private CategoryJpaRepository repository;
    private DevDataSeed seed;
    public DeleteTestHelper(CategoryJpaRepository repository, DevDataSeed seed) {
        this.repository = repository;
        this.seed = seed;
    }

    public long countCategories(){
        return repository.findAll().size();
    }

    public long getCategoryIdByName(String name){
        return repository
                .findFirstCategoryByName(name)
                .orElseThrow()
                .getId();
    }
    public String getRoute(){
        return route;
    }
    public void insertData(){
        seed.insertData();
    }
}
