package com.xtsshop.app.db;


import com.xtsshop.app.db.seed.DevelopmentDataSeed;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabaseConfig {

    private boolean runTestCase = false;

    @Bean
    public CommandLineRunner initDatabase(DevelopmentDataSeed seed) {
        if(!runTestCase){
            seed.insertData();
        }
        return args -> {};
    }
}
