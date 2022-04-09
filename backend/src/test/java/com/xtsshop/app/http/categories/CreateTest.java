package com.xtsshop.app.http.categories;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xtsshop.app.db.entities.Category;
import com.xtsshop.app.db.repositories.CategoryRepository;
import com.xtsshop.app.http.TestCase;
import com.xtsshop.app.request.CategoryRequest;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Comparator;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CreateTest extends TestCase {

	private String route = "/api/categories";
	@Autowired
	private CategoryRepository repository;
	@Test
	void testCaseNormal() throws Exception {
		int count = repository.findAll().size();
		CategoryForm form = new CategoryForm("food", null);
		mvc
			.perform(requestBuilder(HttpMethod.POST, this.route)
					.content(mapper.writeValueAsString(form))
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON)
			)
			.andDo(print())
			.andExpect(status().isCreated());
		assertEquals(1, repository.findAll().size() - count);
	}

	@Test
	void testCaseSubCategory() throws Exception {
		int count = repository.findAll().size();
		long parentId = repository.findAllTopLevel().get(0).getId();
		CategoryRequest form = new CategoryRequest("cake", parentId);
		mvc
				.perform(requestBuilder(HttpMethod.POST, route)
						.content(mapper.writeValueAsString(form))
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON)
				)
				.andDo(print())
				.andExpect(status().isCreated());
		assertEquals(1, repository.findAll().size() - count);
		Category parent = repository.findAll().stream().max(Comparator.comparingInt(a -> (int) a.getId())).orElseThrow().getParent();
		assertEquals(parentId, parent.getId());
	}
}
