package com.xtsshop.app.features.brands;

import com.xtsshop.app.db.seed.TestDataSeed;
import org.springframework.stereotype.Component;

@Component
public class ListAllBrandsTestHelper {

    private TestDataSeed seed;
    private String route = "/api/brands";

    public ListAllBrandsTestHelper(TestDataSeed seed) {
        this.seed = seed;
    }

    public void insertData(){
       seed.insertData();
    }

    public String getRoute(){
        return route;
    }
}
