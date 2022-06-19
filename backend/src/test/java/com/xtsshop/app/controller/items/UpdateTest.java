package com.xtsshop.app.controller.items;

import com.xtsshop.app.advice.exception.RecordNotFoundException;
import com.xtsshop.app.db.entities.Image;
import com.xtsshop.app.db.entities.Item;
import com.xtsshop.app.db.repositories.ImageRepository;
import com.xtsshop.app.db.repositories.ItemRepository;
import com.xtsshop.app.helpers.MultipartPutRequest;
import com.xtsshop.app.TestCase;
import com.xtsshop.app.domain.service.storage.StorageService;
import com.xtsshop.app.form.items.ItemForm;
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
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.Paths;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class UpdateTest extends TestCase {
	@Autowired
	private ItemTestHelper itemTestHelper;

	@Test
	void testCaseNormal() throws Exception {
		long id = itemTestHelper.getRepository().findAll().get(1).getId();
		ItemForm form = new ItemForm();
		form.setName("Gold Scissors");
		form.setPrice(13.3f);
		form.setManufacturer("New Manufacturer 1");
		form.setStack(200);
		mvc
			.perform(addToken(MultipartPutRequest.builder(itemTestHelper.getRouteWithId(), String.valueOf(id))
					.content(mapper.writeValueAsString(form))
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON))
			)
			.andDo(print())
			.andExpect(status().isOk());
		Item item = itemTestHelper.getRepository().findById(id).orElse(null);
		assertNotNull(item);
		assertEquals("Gold Scissors", item.getName());
		assertEquals(13.3f, item.getPrice());
		assertEquals("New Manufacturer 1", item.getManufacturer());
		assertEquals(200, item.getStock());
		assertEquals(2, item.getPriceHistories().size());
	}

	@Test
	void testCaseUpdateImage() throws Exception{
		long id = itemTestHelper.getRepository().findAll().get(1).getId();
		long imgId = itemTestHelper.getRepository().findAll().get(1).getImage().getId();
		String imgPath = itemTestHelper.getRepository().findAll().get(1).getImage().getPath();
		MockMultipartFile file
				= new MockMultipartFile(
				"image",
				"hello.png",
				MediaType.IMAGE_PNG_VALUE,
				"Hello, World!".getBytes()
		);
		mvc
				.perform(addToken(multipart(itemTestHelper.getUpdateImageRoute(), id).file(file)))
				.andExpect(status().isCreated());
		Image image = itemTestHelper.getRepository().findById(id).orElseThrow(()->new RecordNotFoundException("Image not found")).getImage();
		assertTrue(Paths.get(image.getPath()).toFile().exists());
		assertFalse(Paths.get(imgPath).toFile().exists());
		assertNull(itemTestHelper.getImageRepository().findById(imgId).orElse(null));
		assertNotNull(image);
	}

}
