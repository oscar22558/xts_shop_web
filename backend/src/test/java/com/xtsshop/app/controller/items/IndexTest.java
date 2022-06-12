package com.xtsshop.app.controller.items;

import com.xtsshop.app.db.repositories.ImageRepository;
import com.xtsshop.app.db.repositories.ItemRepository;
import com.xtsshop.app.helpers.MediaType;
import com.xtsshop.app.TestCase;
import com.xtsshop.app.domain.service.storage.StorageService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class IndexTest extends TestCase {

	@Autowired
	private Util util;

	@TestConfiguration
	public static class TestConfig{
		@Bean
		public Util util(
				ItemRepository repository,
				ImageRepository imageRepository,
				@Qualifier("ImageStorageService") StorageService storageService
		){
			return new Util(repository, imageRepository, storageService);
		}
	}

	@Test
	void contextLoads() throws Exception {
		util.updateAllItemsPrice();
		mvc.perform(requestBuilder(HttpMethod.GET, util.getRoute()))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_HALJSON));

	}

}
