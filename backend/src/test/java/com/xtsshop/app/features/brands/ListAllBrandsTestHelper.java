package com.xtsshop.app.features.brands;

import com.xtsshop.app.db.seed.DevelopmentDataSeed;
import org.springframework.stereotype.Component;

@Component
public class ListAllBrandsTestHelper {

    private DevelopmentDataSeed seed;
    private String route = "/api/brands";

    public ListAllBrandsTestHelper(DevelopmentDataSeed seed) {
        this.seed = seed;
    }

    public void insertData(){
       seed.insertData();
    }

    public String getRoute(){
        return route;
    }
}
