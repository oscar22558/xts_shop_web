package com.xtsshop.app.http.categories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
				.andExpect(status().isOk());
//				.andExpect(content().string());

	}

}
