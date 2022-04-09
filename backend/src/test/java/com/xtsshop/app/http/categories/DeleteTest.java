package com.xtsshop.app.http.categories;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xtsshop.app.db.entities.Category;
import com.xtsshop.app.db.repositories.CategoryRepository;
import com.xtsshop.app.http.TestCase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class DeleteTest extends TestCase {

	private String route = "/api/categories";
	@Autowired
	private CategoryRepository repository;
	@Test
	void testCaseNormal() throws Exception {
		int count = repository.findAll().size();
		List<Category> entities = repository.findAll();
		String id =  String.valueOf(entities.get(1).getId());
		mvc
			.perform(requestBuilder(HttpMethod.DELETE, route + "/{id}", id)
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON)
			)
			.andDo(print())
			.andExpect(status().isOk());
		assertEquals(-3, repository.findAll().size() - count);

	}

}
