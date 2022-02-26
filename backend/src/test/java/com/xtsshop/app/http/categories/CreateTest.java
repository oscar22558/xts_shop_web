package com.xtsshop.app.http.categories;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xtsshop.app.db.repositories.CategoryRepository;
import com.xtsshop.app.request.CategoryForm;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CreateTest {

	@Autowired
	private MockMvc mockMvc;
	private String route = "/api/categories";
	@Autowired
	private ObjectMapper mapper;
	@Autowired
	private CategoryRepository repository;
	@Test
	void testCaseNormal() throws Exception {
		int count = repository.findAll().size();
		CategoryForm form = new CategoryForm("food", null);
		this.mockMvc
			.perform(post(route)
					.content(mapper.writeValueAsString(form))
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON)
			)
			.andDo(print())
			.andExpect(status().isOk());
		assertEquals(1, repository.findAll().size() - count);

	}

}
