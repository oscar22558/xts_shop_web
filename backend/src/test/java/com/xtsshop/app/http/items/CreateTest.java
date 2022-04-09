package com.xtsshop.app.http.items;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import java.nio.file.Paths;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class CreateTest extends TestCase {

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
	void testCaseNormal() throws Exception {
		int count = util.getRepository().findAll().size();
		int imageCount = util.getImageRepository().findAll().size();
		MockMultipartFile file
				= new MockMultipartFile(
				"image",
				"hello.png",
				MediaType.IMAGE_PNG_VALUE,
				"Hello, World!".getBytes()
		);

		ItemForm form = new ItemForm();
		form.setName("Scissors");
		form.setCategoryId(5L);
		form.setPrice(12.2f);
		form.setManufacturer("Manufacturer 1");
		mvc
			.perform( addToken(multipart(util.getRoute())
					.file(file)
					.param("name", form.getName())
					.param("categoryId", form.getCategoryId().toString())
					.param("price", form.getPrice().toString())
					.param("manufacturer", form.getManufacturer()))
			)
			.andDo(print())
			.andExpect(status().isCreated())
			.andExpect(jsonPath("$.imgUrl", is(
					util.getStorageService().url(util.latestImage().getPath())
			)))
			.andExpect(jsonPath("$.name", is("Scissors")))
			.andExpect(jsonPath("$.price", is(12.2)))
			.andExpect(jsonPath("$.manufacturer", is("Manufacturer 1")));
		assertEquals(1, util.getRepository().findAll().size() - count);
		assertEquals(1, util.getImageRepository().findAll().size() - imageCount);

		assertTrue(Paths.get(util.latestImage().getPath()).toFile().exists());
	}

}
