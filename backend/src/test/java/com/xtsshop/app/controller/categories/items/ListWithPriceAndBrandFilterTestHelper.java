package com.xtsshop.app.controller.categories.items;

import com.xtsshop.app.dataset.DataSet;
import com.xtsshop.app.db.repositories.BrandJpaRepository;
import com.xtsshop.app.db.repositories.CategoryJpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;

@Component
public class ListWithPriceAndBrandFilterTestHelper {

    private CategoryJpaRepository categoryJpaRepository;
    private BrandJpaRepository brandJpaRepository;
    private MockMvc mockMvc;
    private String route = "/api/categories";
    private DataSet dataSet;
    public ListWithPriceAndBrandFilterTestHelper(MockMvc mockMvc, CategoryJpaRepository categoryJpaRepository, BrandJpaRepository brandJpaRepository, DataSet dataSet){
        this.mockMvc = mockMvc;
        this.categoryJpaRepository = categoryJpaRepository;
        this.brandJpaRepository = brandJpaRepository;
        this.dataSet = dataSet;
    }
    public MockMvc getMockMvc(){
        return mockMvc;
    }
    public long getFoodCategoryId(){
        return categoryJpaRepository.findAll().stream().findFirst().orElseThrow().getId();
    }
    public long getMobileDevicePeripheralId(){
        return categoryJpaRepository.findAll().get(4).getId();
    }
    public long getBrandId(){
        return brandJpaRepository.findAll().get(0).getId();
    }

    public String getRoute(Long categoryId){
        return route + "/" + categoryId+ "/items";
    }
    public void insertData(){
        dataSet.insertData();
    }
}
