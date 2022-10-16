package com.xtsshop.app.db;


import com.xtsshop.app.db.seed.DevDataSeed;
import com.xtsshop.app.db.seed.OrderData;
import com.xtsshop.app.db.seed.UserPasswordEncryptor;
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
    public CommandLineRunner initDatabase(UserPasswordEncryptor userPasswordEncryptor) {
        Logger logger = LoggerFactory.getLogger(LoadDatabaseConfig.class);
        logger.info("Set up database for test environment");
        userPasswordEncryptor.encrypt("marry1234");
        userPasswordEncryptor.encrypt("ken1234");
        return args -> {};
    }
}
