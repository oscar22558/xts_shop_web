package com.xtsshop.app.features.categories.items;

import com.xtsshop.app.db.repositories.CategoryJpaRepository;
import com.xtsshop.app.db.seed.TestDataSeed;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.test.web.servlet.MockMvc;

@TestComponent
public class ListAllCategoryItemTestHelper {
    private CategoryJpaRepository categoryJpaRepository;
    private MockMvc mockMvc;
    private String route = "/api/categories";
    private TestDataSeed seed;
    public ListAllCategoryItemTestHelper(MockMvc mockMvc, CategoryJpaRepository categoryJpaRepository, TestDataSeed seed){
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
