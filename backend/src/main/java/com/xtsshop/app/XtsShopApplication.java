package com.xtsshop.app;

import com.xtsshop.app.domain.service.storage.StorageProperties;
import com.xtsshop.app.domain.service.storage.StorageService;
import com.xtsshop.app.domain.service.storage.StorageUrlProperties;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
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
						.allowedOrigins("http://localhost:3000")
						.allowedMethods("GET", "POST", "DELETE", "PUT");
			}
		};
	}
	@Bean
	CommandLineRunner init(@Qualifier("FileStorageService") StorageService storageService, @Qualifier("ImageStorageService") StorageService imageStorageService) {
		return (args) -> {
			storageService.deleteAll();
			storageService.init();
			imageStorageService.init();
		};
	}
}
