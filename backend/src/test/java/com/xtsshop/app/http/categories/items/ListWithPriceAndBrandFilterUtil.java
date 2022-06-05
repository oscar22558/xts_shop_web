package com.xtsshop.app.http.categories.items;

import com.xtsshop.app.db.repositories.BrandRepository;
import com.xtsshop.app.db.repositories.CategoryRepository;
import org.springframework.test.web.servlet.MockMvc;

public class ListWithPriceAndBrandFilterUtil {

    private CategoryRepository categoryRepository;
    private BrandRepository brandRepository;
    private MockMvc mockMvc;
    private String route = "/api/categories";
    public ListWithPriceAndBrandFilterUtil(MockMvc mockMvc, CategoryRepository categoryRepository, BrandRepository brandRepository){
        this.mockMvc = mockMvc;
        this.categoryRepository = categoryRepository;
        this.brandRepository = brandRepository;
    }
    public MockMvc getMockMvc(){
        return mockMvc;
    }
    public long getCategoryId(){
        return categoryRepository.findAll().stream().findFirst().orElseThrow().getId();
    }
    public long getBrandId(){
        return brandRepository.findAll().get(0).getId();
    }
    public float getMaxPrice(){
        return 5f;
    }
    public float getMinPrice(){
        return 1f;
    }
    public String getRoute(){
        return route + "/items";
    }
}
