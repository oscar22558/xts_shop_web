package com.xtsshop.app.config;


import com.xtsshop.app.db.seed.DevelopmentDataSeed;
import com.xtsshop.app.db.repositories.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {

    private boolean localDevelopment = false;

    @Bean
    public CommandLineRunner initDatabase(DevelopmentDataSeed seed) {
        if(localDevelopment){
            seed.insertData();
        }
        return args -> {};
    }
}
