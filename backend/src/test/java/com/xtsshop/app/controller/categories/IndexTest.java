package com.xtsshop.app.controller.categories;

import com.xtsshop.app.helpers.MediaType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class IndexTest {

	@Autowired
	private IndexTestHelper helper;

	@Test
	public void testCaseResponseOk() throws Exception {
		helper.insertData();
		ResultActions response = helper.getMockMvc().perform(get(helper.getRoute())).andDo(print());
		assertResponseOk(response);
	}

	@Test
	public void testCaseResponseHasCorrectSize() throws Exception {
		helper.insertData();
		ResultActions response = helper.getMockMvc().perform(get(helper.getRoute())).andDo(print());
		assertResponseHasSize(response, 2);
	}

	@Test
	public void testCaseResponseContainFoodCategory() throws Exception{
		helper.insertData();
		ResultActions response = helper.getMockMvc().perform(get(helper.getRoute())).andDo(print());
		assertResponseContainFoodCategory(response);
	}

	@Test
	public void testCaseResponseContainToolCategory() throws Exception{
		helper.insertData();
		ResultActions response = helper.getMockMvc().perform(get(helper.getRoute())).andDo(print());
		assertResponseContainToolCategory(response);
	}

	@Test
	public void testCaseFoodCategoryContainCorrectSubCategories() throws Exception{
		helper.insertData();
		ResultActions response = helper.getMockMvc().perform(get(helper.getRoute())).andDo(print());
		assertFoodCategoryContainCorrectSubCategories(response);
	}
	private void assertResponseOk(ResultActions response) throws Exception {
				response.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_HALJSON));
	}
	private void assertResponseHasSize(ResultActions response, int size) throws Exception {
		response.andExpect(jsonPath("$._embedded.categoryModelList", hasSize(2)));
	}
	private void assertResponseContainFoodCategory(ResultActions response) throws Exception {
		response.andExpect(jsonPath("$._embedded.categoryModelList.[0].name", is("food")));
	}
	private void assertResponseContainToolCategory(ResultActions response) throws Exception {
		response.andExpect(jsonPath("$._embedded.categoryModelList.[1].name", is("tools")));
	}
	private void assertFoodCategoryContainCorrectSubCategories(ResultActions response) throws Exception {
		response
				.andExpect(jsonPath("$._embedded.categoryModelList.[0].subCategories[0].id", is(3)))
				.andExpect(jsonPath("$._embedded.categoryModelList.[0].subCategories[0].name", is("fruit")))
				.andExpect(jsonPath("$._embedded.categoryModelList.[0].subCategories[1].id", is(4)))
				.andExpect(jsonPath("$._embedded.categoryModelList.[0].subCategories[1].name", is("meat")));
	}
}
