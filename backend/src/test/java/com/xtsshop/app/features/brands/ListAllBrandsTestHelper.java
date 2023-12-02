package com.xtsshop.app.features.brands;

import com.xtsshop.app.db.seed.DevDataSeed;
import org.springframework.stereotype.Component;

@Component
public class ListAllBrandsTestHelper {

    private DevDataSeed seed;
    private String route = "/api/brands";

    public ListAllBrandsTestHelper(DevDataSeed seed) {
        this.seed = seed;
    }

    public void insertData(){
       seed.insertData();
    }

    public String getRoute(){
        return route;
    }
}
