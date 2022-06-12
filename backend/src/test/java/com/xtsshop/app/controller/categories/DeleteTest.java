package com.xtsshop.app.controller.categories;

import com.xtsshop.app.db.entities.Category;
import com.xtsshop.app.db.repositories.CategoryRepository;
import com.xtsshop.app.TestCase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class DeleteTest extends TestCase {

	@Autowired
	private DeleteTestHelper helper;

	@Test
	void testCaseResponseOk() throws Exception {
		helper.insertData();
		ResultActions response = mvc.perform(buildRequest()).andDo(print());
		assertResponseOk(response);
	}

	@Test
	void testCaseCategoriesAreDeleted() throws Exception {
		helper.insertData();
		mvc.perform(buildRequest()).andDo(print());
		assertCategoryAndSubCategoriesDeleted();
	}

	private MockHttpServletRequestBuilder buildRequest() throws Exception {
		long categoryId = helper.getCategoryIdByName("food");
		return requestBuilder(HttpMethod.DELETE, helper.getRoute(), categoryId+"")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
	}

	private void assertResponseOk(ResultActions response) throws Exception {
		response
				.andExpect(status().isOk());
	}

	private void assertCategoryAndSubCategoriesDeleted() throws Exception{
		assertEquals(5, helper.countCategories());
	}
}
