package com.xtsshop.app.controller.categories;

import com.xtsshop.app.db.repositories.CategoryJpaRepository;
import com.xtsshop.app.db.seed.DevelopmentDataSeed;
import org.springframework.stereotype.Component;

@Component
public class DeleteTestHelper {
    private String route = "/api/categories/{id}";
    private CategoryJpaRepository repository;
    private DevelopmentDataSeed seed;
    public DeleteTestHelper(CategoryJpaRepository repository, DevelopmentDataSeed seed) {
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
