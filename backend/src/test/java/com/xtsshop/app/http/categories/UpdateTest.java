package com.xtsshop.app.http.categories;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xtsshop.app.db.entities.Category;
import com.xtsshop.app.db.repositories.CategoryRepository;
import com.xtsshop.app.request.CategoryForm;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class UpdateTest {

	@Autowired
	private MockMvc mockMvc;
	private String route = "/api/categories";
	@Autowired
	private ObjectMapper mapper;
	@Autowired
	private CategoryRepository repository;
	@Test
	void testCaseNormal() throws Exception {
		long id =  repository.findAll().get(1).getId();
		this.mockMvc
			.perform(put(route + "/{id}", String.valueOf(id))
					.content(mapper.writeValueAsString(new CategoryForm("Container", null)))
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON)
			)
			.andDo(print())
			.andExpect(status().isOk());
		assertEquals("Container", repository.findAll().get(1).getName());
	}

	@Test
	void testCaseChangeParentCategory() throws Exception {
		long id =  repository.findAll().get(2).getId();
		this.mockMvc
				.perform(put(route + "/{id}", String.valueOf(id))
						.content(mapper.writeValueAsString(new CategoryForm(null, 2L)))
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON)
				)
				.andDo(print())
				.andExpect(status().isOk());
		repository.flush();
		assertNotNull(repository.findAll().get(2).getParent());
		assertEquals("tools", repository.findAll().get(2).getParent().getName());
	}

	@BeforeEach
	void initDB(){

	}
}
