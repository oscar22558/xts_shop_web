package com.xtsshop.app.http.items;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xtsshop.app.db.entities.Category;
import com.xtsshop.app.db.entities.Item;
import com.xtsshop.app.db.repositories.CategoryRepository;
import com.xtsshop.app.db.repositories.ImageRepository;
import com.xtsshop.app.db.repositories.ItemRepository;
import com.xtsshop.app.http.TestCase;
import com.xtsshop.app.service.storage.StorageService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class DeleteTest extends TestCase {

	private String route = "/api/items/{id}";
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
	void testCaseNormal() throws Exception {
		int count = util.getRepository().findAll().size();
		Item entity = util.getRepository().findAll().get(0);
		String id =  String.valueOf(entity.getId());
		mvc
			.perform(requestBuilder(HttpMethod.DELETE, route, id))
			.andDo(print())
			.andExpect(status().isOk());
		assertEquals(-1, util.getRepository().findAll().size() - count);

	}

}
