package com.xtsshop.app.controller.categories.items;

import com.xtsshop.app.db.repositories.CategoryJpaRepository;
import com.xtsshop.app.db.seed.DevelopmentDataSeed;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;

@Component
public class ListAllTestHelper {
    private CategoryJpaRepository categoryJpaRepository;
    private MockMvc mockMvc;
    private String route = "/api/categories";
    private DevelopmentDataSeed seed;
    public ListAllTestHelper(MockMvc mockMvc, CategoryJpaRepository categoryJpaRepository, DevelopmentDataSeed seed){
        this.mockMvc = mockMvc;
        this.categoryJpaRepository = categoryJpaRepository;
        this.seed = seed;
    }
    public long getCategoryIdByName(String name){
        return categoryJpaRepository
                .findAll()
                .stream()
                .filter(category -> category.getName().equals(name))
                .findFirst()
                .orElseThrow()
                .getId();
    }

    public String getIndexRoute(long categoryId){
        return route + "/" + categoryId + "/items";
    }
    public MockMvc getMockMvc(){
        return mockMvc;
    }
    public void insertData(){
        seed.insertData();
    }
}
