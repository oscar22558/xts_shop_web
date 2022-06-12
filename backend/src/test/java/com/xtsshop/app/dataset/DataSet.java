package com.xtsshop.app.dataset;

import com.xtsshop.app.db.seed.DevelopmentDataSeed;
import org.springframework.stereotype.Component;

@Component
public class DataSet {
    private DevelopmentDataSeed seed;

    public DataSet(DevelopmentDataSeed seed) {
        this.seed = seed;
    }

    public void insertData(){
        seed.insertData();
    }
}
