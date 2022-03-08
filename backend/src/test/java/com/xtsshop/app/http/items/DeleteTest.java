package com.xtsshop.app.http.items;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xtsshop.app.db.entities.Category;
import com.xtsshop.app.db.entities.Item;
import com.xtsshop.app.db.repositories.CategoryRepository;
import com.xtsshop.app.db.repositories.ItemRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
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
class DeleteTest {

	@Autowired
	private MockMvc mockMvc;
	private String route = "/api/items/{id}";
	@Autowired
	private ObjectMapper mapper;
	@Autowired
	private ItemRepository repository;
	@Test
	void testCaseNormal() throws Exception {
		int count = repository.findAll().size();
		Item entity = repository.findAll().get(0);
		String id =  String.valueOf(entity.getId());
		this.mockMvc
			.perform(delete(route, id)
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON)
			)
			.andDo(print())
			.andExpect(status().isOk());
		assertEquals(-1, repository.findAll().size() - count);

	}

}
