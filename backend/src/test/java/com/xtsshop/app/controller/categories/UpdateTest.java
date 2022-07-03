package com.xtsshop.app.controller.categories;

import com.xtsshop.app.advice.exception.RecordNotFoundException;
import com.xtsshop.app.db.repositories.CategoryRepository;
import com.xtsshop.app.TestCase;
import com.xtsshop.app.db.seed.DevelopmentDataSeed;
import com.xtsshop.app.domain.request.categories.CategoryRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class UpdateTest extends TestCase {
	private String route = "/api/categories";
	@Autowired
	private CategoryRepository repository;

	@Autowired
	private DevelopmentDataSeed seed;

	@Test
	public void testCaseNormal() throws Exception {
		insertData();

		long id =  repository.findAll().get(1).getId();
		mvc
			.perform(requestBuilder(HttpMethod.PUT, route + "/{id}", String.valueOf(id))
					.content(mapper.writeValueAsString(new CategoryRequest("Container", null)))
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON)
			)
			.andDo(print())
			.andExpect(status().isOk());
		assertEquals("Container", repository.findAll().get(1).getName());
	}

	@Test
	public void testCaseChangeParentCategory() throws Exception {
		insertData();
		long id =  repository.findAll().get(2).getId();
		long originalParentId = repository.findAll().get(2).getParent().getId();
		mvc
				.perform(requestBuilder(HttpMethod.PUT, route + "/{id}", String.valueOf(id))
						.content(mapper.writeValueAsString(new CategoryRequest(null, 2L)))
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON)
				)
				.andDo(print())
				.andExpect(status().isOk());

		assertNotNull(repository.findAll().get(2).getParent());
		assertEquals("tools", repository.findAll().get(2).getParent().getName());
		assertNull(repository.findById(originalParentId).orElseThrow(()-> new RecordNotFoundException("")).getSubCategories().stream().filter(category->category.getId() == id).findAny().orElse(null));
	}

	public void insertData(){
		seed.insertData();
	}
}
