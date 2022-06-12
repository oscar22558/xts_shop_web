package com.xtsshop.app.controller.categories.items;

import com.xtsshop.app.db.repositories.CategoryRepository;
import com.xtsshop.app.db.seed.DevelopmentDataSeed;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;

@Component
public class ListAllTestHelper {
    private CategoryRepository categoryRepository;
    private MockMvc mockMvc;
    private String route = "/api/categories";
    private DevelopmentDataSeed seed;
    public ListAllTestHelper(MockMvc mockMvc, CategoryRepository categoryRepository, DevelopmentDataSeed seed){
        this.mockMvc = mockMvc;
        this.categoryRepository = categoryRepository;
        this.seed = seed;
    }
    public long getCategoryIdByName(String name){
        return categoryRepository
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
