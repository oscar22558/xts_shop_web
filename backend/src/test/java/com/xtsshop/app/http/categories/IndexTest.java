package com.xtsshop.app.http.categories;

import com.xtsshop.app.http.MediaType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class IndexTest {

	@Autowired
	private MockMvc mockMvc;
	private String route = "/api/categories";

	@Test
	void contextLoads() throws Exception {
		this.mockMvc.perform(get(route))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_HALJSON))
				.andExpect(jsonPath("$._embedded.categoryModelList", hasSize(2)))
				.andExpect(jsonPath("$._embedded.categoryModelList.[0].id", is(1)))
				.andExpect(jsonPath("$._embedded.categoryModelList.[0].name", is("food")))
				.andExpect(jsonPath("$._embedded.categoryModelList.[0].subCategories", hasSize(2)))
				.andExpect(jsonPath("$._embedded.categoryModelList.[0].subCategories[0].id", is(3)))
				.andExpect(jsonPath("$._embedded.categoryModelList.[0].subCategories[0].name", is("fruit")))
				.andExpect(jsonPath("$._embedded.categoryModelList.[0].subCategories[1].id", is(4)))
				.andExpect(jsonPath("$._embedded.categoryModelList.[0].subCategories[1].name", is("meat")))
				.andExpect(jsonPath("$._embedded.categoryModelList.[1].id", is(2)))
				.andExpect(jsonPath("$._embedded.categoryModelList.[1].name", is("tools")))
				.andExpect(jsonPath("$._embedded.categoryModelList.[1].subCategories[0].id", is(5)))
				.andExpect(jsonPath("$._embedded.categoryModelList.[1].subCategories[0].name", is("mobile device peripheral")))
				.andExpect(jsonPath("$._embedded.categoryModelList.[1].subCategories[1].id", is(6)))
				.andExpect(jsonPath("$._embedded.categoryModelList.[1].subCategories[1].name", is("drawing tools")));

	}

}
