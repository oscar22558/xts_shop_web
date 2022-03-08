package com.xtsshop.app.http.items;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xtsshop.app.db.entities.Image;
import com.xtsshop.app.db.entities.Item;
import com.xtsshop.app.db.repositories.ImageRepository;
import com.xtsshop.app.db.repositories.ItemRepository;
import com.xtsshop.app.http.MultipartPutRequest;
import com.xtsshop.app.service.storage.StorageService;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class UpdateTest {
	Logger logger = LoggerFactory.getLogger(UpdateTest.class);
	@Autowired
	private Util util;

	@TestConfiguration
	public static class TestConfig{
		@Bean
		public Util util(
				MockMvc mvc,
				ObjectMapper mapper,
				ItemRepository repository,
				ImageRepository imageRepository,
				@Qualifier("ImageStorageService")StorageService storageService
				){
			return new Util(mvc, mapper, repository, imageRepository, storageService);
		}
	}
	@Test
	void testCaseNormal() throws Exception {
		long id = util.getRepository().findAll().get(1).getId();
		ItemForm form = new ItemForm();
		form.setName("Gold Scissors");
		form.setPrice(13.3f);
		form.setManufacturer("New Manufacturer 1");
		util.getMvc()
			.perform(MultipartPutRequest.builder(util.getRouteWithId(), String.valueOf(id))
					.content(util.getMapper().writeValueAsString(form))
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON)
			)
			.andDo(print())
			.andExpect(status().isOk());
		Item item = util.getRepository().findById(id).orElse(null);
		assertNotNull(item);
		assertEquals("Gold Scissors", item.getName());
		assertEquals(13.3f, item.getPrice());
		assertEquals("New Manufacturer 1", item.getManufacturer());
	}

	@Test
	void testCaseUpdateImage() throws Exception{
		long id = util.getRepository().findAll().get(1).getId();
		long imgId = util.getRepository().findAll().get(1).getImage().getId();
		String imgPath = util.getRepository().findAll().get(1).getImage().getPath();
		MockMultipartFile file
				= new MockMultipartFile(
				"image",
				"hello.png",
				MediaType.IMAGE_PNG_VALUE,
				"Hello, World!".getBytes()
		);
		util.getMvc()
				.perform(multipart(util.getUpdateImageRoute(), id).file(file))
				.andExpect(status().isCreated());
		Image image = util.getRepository().findById(id).orElseThrow(null).getImage();
		assertTrue(Paths.get(image.getPath()).toFile().exists());
		assertFalse(Paths.get(imgPath).toFile().exists());
		assertNull(util.getImageRepository().findById(imgId).orElse(null));
		assertNotNull(image);
	}

}