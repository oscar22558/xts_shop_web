package com.xtsshop.app.db;


import com.xtsshop.app.db.seed.DevelopmentDataSeed;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabaseConfig {

    private boolean localDevelopment = true;

    @Bean
    public CommandLineRunner initDatabase(DevelopmentDataSeed seed) {
        if(localDevelopment){
            seed.insertData();
        }
        return args -> {};
    }
}
