package com.xtsshop.app.dataset;

import com.xtsshop.app.db.seed.DevDataSeed;
import org.springframework.stereotype.Component;

@Component
public class DataSet {
    private DevDataSeed seed;

    public DataSet(DevDataSeed seed) {
        this.seed = seed;
    }

    public void insertData(){
        seed.insertData();
    }
}
