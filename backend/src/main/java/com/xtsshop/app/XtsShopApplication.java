package com.xtsshop.app;

import com.xtsshop.app.features.storage.StorageProperties;
import com.xtsshop.app.features.storage.StorageService;
import com.xtsshop.app.features.storage.StorageUrlProperties;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EnableConfigurationProperties({StorageProperties.class, StorageUrlProperties.class})
public class XtsShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(XtsShopApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
						.allowedOrigins("*")
						.allowedMethods("GET", "POST", "DELETE", "PUT", "PATCH", "OPTIONS");
			}
		};
	}
	@Bean
	public CommandLineRunner init(@Qualifier("FileStorageService") StorageService storageService, @Qualifier("ImageStorageService") StorageService imageStorageService) {
		return (args) -> {
			try{
			imageStorageService.init();
			}catch(Exception ex){
				
			}
		};
	}
}
