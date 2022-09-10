package com.xtsshop.app.features.items;

import com.xtsshop.app.DependencyTestConfig;
import com.xtsshop.app.LoadDatabaseTestConfig;
import com.xtsshop.app.TestCase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.nio.file.Paths;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Import({

		ItemTestHelper.class
})
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class CreateTest extends TestCase {

	@Autowired
	private ItemTestHelper itemTestHelper;

	@Test
	public void testIsCreated() throws Exception {
		itemTestHelper.insertData();
		ResultActions response = sendMockRequest();
		assertResponseIsCreated(response);
	}

	@Test
	public void testResponseContainCreatedItem() throws Exception{
		itemTestHelper.insertData();
		ResultActions response = sendMockRequest();
		assertResponseContainCreatedItem(response);
	}

	@Test
	public void testResponseContainItemImageUrl() throws Exception{
		itemTestHelper.insertData();
		ResultActions response = sendMockRequest();
		assertResponseContainItemImageUrl(response);
	}

	@Test
	public void testResponseItemHasStock() throws Exception{
		itemTestHelper.insertData();
		ResultActions response = sendMockRequest();
		assertResponseItemHasStock(response);
	}

	@Test
	public void testOneItemInserted() throws Exception{
		itemTestHelper.insertData();
		sendMockRequest();
		assertOneItemInserted();
	}

	@Test
	public void testItemImageInserted() throws Exception{
		itemTestHelper.insertData();
		sendMockRequest();
		assertItemImageInserted();
	}

	@Test
	public void testItemImageIsInStorage() throws Exception{
		itemTestHelper.insertData();
		sendMockRequest();
		assertItemImageIsInStorage();
	}

	private ResultActions sendMockRequest() throws Exception{
		return mvc.perform(buildCreateItemRequestWithStock())
				.andDo(print());
	}

	public MockHttpServletRequestBuilder buildCreateItemRequestWithStock() throws Exception{
		return addToken(multipart(itemTestHelper.getRoute())
				.file(itemTestHelper.getMockImageFile())
				.param("name", "Scissors")
				.param("categoryId", "5")
				.param("price", "12.2f")
				.param("manufacturer", "Manufacturer 1")
				.param("stack", "100")
				.param("brandId", itemTestHelper.getBrandId(0))
		);
	}

	private void assertResponseIsCreated(ResultActions response) throws Exception{
			response.andExpect(status().isCreated());
	}

	private void assertResponseContainCreatedItem(ResultActions response) throws Exception{
		response.andExpect(jsonPath("$.name", is("Scissors")))
				.andExpect(jsonPath("$.price.value", is(12.2)))
				.andExpect(jsonPath("$.manufacturer", is("Manufacturer 1")));
	}

	private void assertResponseItemHasStock(ResultActions response) throws Exception{
		response.andExpect(jsonPath("$.stock", is(100)));
	}

	private void assertResponseContainItemImageUrl(ResultActions response) throws Exception{
		response.andExpect(jsonPath("$.imgUrl", is(
				itemTestHelper
						.getFilePathToUrlConverter()
						.getUrl(itemTestHelper.latestImage().getPath())
		)));
	}

	private void assertOneItemInserted(){
		assertEquals(5, itemTestHelper.getRepository().findAll().size());
	}

	private void assertItemImageInserted(){
		assertEquals(5, itemTestHelper.getImageJpaRepository().findAll().size());
	}

	private void assertItemImageIsInStorage(){
		assertTrue(Paths.get(itemTestHelper.latestImage().getPath()).toFile().exists());
	}

	@Test
	public void testCreateItemHasNoStockIsCreated() throws Exception {
		itemTestHelper.insertData();
		ResultActions response = sendMockCreateItemRequestWithoutStock();
		assertResponseIsCreated(response);
	}

	@Test
	public void testCreateItemHasNoStock() throws Exception{
		itemTestHelper.insertData();
		ResultActions response = sendMockCreateItemRequestWithoutStock();
		assertResponseItemHasNoStock(response);
	}

	public MockHttpServletRequestBuilder buildCreateItemRequestWithoutStock() throws Exception{
		return addToken(multipart(itemTestHelper.getRoute())
				.file(itemTestHelper.getMockImageFile())
				.param("name", "Scissors")
				.param("categoryId", "5")
				.param("price", "12.2f")
				.param("manufacturer", "Manufacturer 1")
				.param("brandId", itemTestHelper.getBrandId(0))
		);
	}

	public ResultActions sendMockCreateItemRequestWithoutStock() throws Exception{
		return mvc.perform(buildCreateItemRequestWithoutStock()).andDo(print());
	}

	public void assertResponseItemHasNoStock(ResultActions response) throws Exception{
		response.andExpect(jsonPath("$.stock", is(0)));
	}




}
