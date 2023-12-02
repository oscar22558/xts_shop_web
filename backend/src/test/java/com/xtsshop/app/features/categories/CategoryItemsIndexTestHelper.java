package com.xtsshop.app.features.categories;

import com.xtsshop.app.db.seed.DevDataSeed;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;

@TestComponent
@Transactional
public class CategoryItemsIndexTestHelper {

    private MockMvc mockMvc;
    private String route = "/api/categories";
    private DevDataSeed seed;

    public CategoryItemsIndexTestHelper(MockMvc mockMvc, DevDataSeed seed) {
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
