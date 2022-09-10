package com.xtsshop.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

@TestConfiguration
@Profile("dev")
public class LoadDatabaseTestConfig {
    @Bean
    @Primary
    public CommandLineRunner initDatabase() {
        Logger logger = LoggerFactory.getLogger(LoadDatabaseTestConfig.class);
        logger.info("Set up database for test environment");
        return args -> {};
    }
}
