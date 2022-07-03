package com.xtsshop.app.controller.items;

import com.xtsshop.app.TestCase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class IndexTest extends TestCase {

	@Autowired
	private ItemTestHelper itemTestHelper;

	@Test
	public void testResponseOk() throws Exception {
		itemTestHelper.insertData();
		itemTestHelper.updateAllItemsPrice();
		ResultActions response = sendMockRequest();
		assertResponseIsOk(response);
	}

	@Test
	public void testResponseContainsItems() throws Exception {
		itemTestHelper.insertData();
		ResultActions response = sendMockRequest();
		assertResponseContainItems(response);
	}

	@Test
	public void testResponseContainsApple() throws Exception {
		itemTestHelper.insertData();
		ResultActions response = sendMockRequest();
		assertResponseContainApple(response);
	}

	@Test
	public void testResponseAppleHasLatestPrice() throws Exception{
		itemTestHelper.insertData();
		itemTestHelper.updateAllItemsPrice();
		ResultActions response = sendMockRequest();
		assertApplePriceIsLatest(response);
	}

	private ResultActions sendMockRequest() throws Exception{
		return mvc.perform(requestBuilder(HttpMethod.GET, itemTestHelper.getRoute()))
						.andDo(print());

	}

	private void assertResponseIsOk(ResultActions response) throws Exception{
		response.andExpect(status().isOk());

	}

	private void assertResponseContainItems(ResultActions response) throws Exception{
		response.andExpect(jsonPath("$._embedded.itemRepresentationModelList", hasSize(4)));
	}

	private void assertResponseContainApple(ResultActions response) throws Exception{
		response.andExpect(jsonPath("$._embedded.itemRepresentationModelList[0].name", is("apple")));
		response.andExpect(jsonPath("$._embedded.itemRepresentationModelList[0].price.value", is(12.2)));
		response.andExpect(jsonPath("$._embedded.itemRepresentationModelList[0].stock", is(100)));
	}


	private void assertApplePriceIsLatest(ResultActions response) throws Exception{
		response.andExpect(jsonPath("$._embedded.itemRepresentationModelList[0].name", is("apple")));
		response.andExpect(jsonPath("$._embedded.itemRepresentationModelList[0].price.value", is(22.2)));
	}
}
