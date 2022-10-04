package com.xtsshop.app.dataset;

import com.xtsshop.app.db.seed.TestDataSeed;
import org.springframework.stereotype.Component;

@Component
public class DataSet {
    private TestDataSeed seed;

    public DataSet(TestDataSeed seed) {
        this.seed = seed;
    }

    public void insertData(){
        seed.insertData();
    }
}
