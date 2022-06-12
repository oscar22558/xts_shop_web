package com.xtsshop.app.controller.categories;

import com.xtsshop.app.db.seed.DevelopmentDataSeed;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;

@Component
public class IndexTestHelper {

    private MockMvc mockMvc;
    private String route = "/api/categories";
    private DevelopmentDataSeed seed;

    public IndexTestHelper(MockMvc mockMvc, DevelopmentDataSeed seed) {
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
