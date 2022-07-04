package com.xtsshop.app.controller.categories.items;

import com.xtsshop.app.dataset.DataSet;
import com.xtsshop.app.db.repositories.BrandRepository;
import com.xtsshop.app.db.repositories.CategoryRepository;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;

@Component
public class ListWithPriceAndBrandFilterTestHelper {

    private CategoryRepository categoryRepository;
    private BrandRepository brandRepository;
    private MockMvc mockMvc;
    private String route = "/api/categories";
    private DataSet dataSet;
    public ListWithPriceAndBrandFilterTestHelper(MockMvc mockMvc, CategoryRepository categoryRepository, BrandRepository brandRepository, DataSet dataSet){
        this.mockMvc = mockMvc;
        this.categoryRepository = categoryRepository;
        this.brandRepository = brandRepository;
        this.dataSet = dataSet;
    }
    public MockMvc getMockMvc(){
        return mockMvc;
    }
    public long getFoodCategoryId(){
        return categoryRepository.findAll().stream().findFirst().orElseThrow().getId();
    }
    public long getMobileDevicePeripheralId(){
        return categoryRepository.findAll().get(4).getId();
    }
    public long getBrandId(){
        return brandRepository.findAll().get(0).getId();
    }

    public String getRoute(Long categoryId){
        return route + "/" + categoryId+ "/items";
    }
    public void insertData(){
        dataSet.insertData();
    }
}
