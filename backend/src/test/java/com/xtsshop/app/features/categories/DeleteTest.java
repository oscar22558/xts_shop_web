package com.xtsshop.app.features.categories;

import com.xtsshop.app.DependencyTestConfig;
import com.xtsshop.app.LoadDatabaseTestConfig;
import com.xtsshop.app.TestCase;
import com.xtsshop.app.features.categories.items.ListAllCategoryItemTestHelper;
import com.xtsshop.app.features.orders.data.FakeNewUserDataSet;
import com.xtsshop.app.features.orders.data.FakeOrderDataSetBuilder;
import com.xtsshop.app.features.orders.data.FakeOrderedItemDataSet;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc@Import({
		DeleteTestHelper.class
})
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
		assertEquals(6, helper.countCategories());
	}
}
