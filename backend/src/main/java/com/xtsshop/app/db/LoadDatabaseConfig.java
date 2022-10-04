package com.xtsshop.app.db;


import com.xtsshop.app.db.seed.TestDataSeed;
import com.xtsshop.app.db.seed.OrderData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class LoadDatabaseConfig {

    @Bean
    public CommandLineRunner initDatabase(TestDataSeed seed, OrderData orderData) {
        Logger logger = LoggerFactory.getLogger(LoadDatabaseConfig.class);
        logger.info("Set up database for test environment");
        seed.insertData();
        orderData.insert();
        return args -> {};
    }
}
