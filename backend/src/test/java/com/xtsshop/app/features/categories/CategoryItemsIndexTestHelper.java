package com.xtsshop.app.features.categories;

import com.xtsshop.app.db.seed.DevelopmentDataSeed;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;

@TestComponent
@Transactional
public class CategoryItemsIndexTestHelper {

    private MockMvc mockMvc;
    private String route = "/api/categories";
    private DevelopmentDataSeed seed;

    public CategoryItemsIndexTestHelper(MockMvc mockMvc, DevelopmentDataSeed seed) {
        this.mockMvc = mockMvc;
        this.seed = seed;
    }

    public MockMvc getMockMvc() {
        return mockMvc;
    }

    public String getRoute() {
        return route;
    }

    public void insertData(){
        seed.insertData();
    }
}
