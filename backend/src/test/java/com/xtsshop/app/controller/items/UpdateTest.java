package com.xtsshop.app.controller.items;

import com.xtsshop.app.helpers.MultipartPutRequest;
import com.xtsshop.app.TestCase;
import com.xtsshop.app.form.items.UpdateItemTestForm;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class UpdateTest extends TestCase {
	@Autowired
	private ItemTestHelper itemTestHelper;

	private UpdateItemTestForm updateItemTestForm;
	private ResultActions response;

	@Test
	public void testWhenSentUpdateItemNameRequestThenResponseOk() throws Exception {
		itemTestHelper.insertData();
		buildFormToUpdateItemName();
		response = sendRequestToUpdateItem();
		assertResponseOk();
	}

	@Test
	public void testWhenSentUpdateItemNameRequestThenItemNameUpdated() throws Exception {
		itemTestHelper.insertData();
		buildFormToUpdateItemName();
		response = sendRequestToUpdateItem();
		assertItemName();
	}

	@Test
	public void testWhenSentUpdateItemPriceRequestThenItemPriceUpdated() throws Exception {
		itemTestHelper.insertData();
		buildFormToUpdateItemPrice();
		response = sendRequestToUpdateItem();
		assertItemPrice();
	}

	@Test
	public void testWhenSentUpdateItemPriceRequestThenItemPriceHistoryCountIncrease() throws Exception {
		itemTestHelper.insertData();
		buildFormToUpdateItemPrice();
		response = sendRequestToUpdateItem();
		assertItemPriceHistoryCount();
	}

	@Test
	public void testWhenSentUpdateItemManufacturerRequestThenItemManufacturerUpdated() throws Exception {
		itemTestHelper.insertData();
		buildFormToUpdateItemManufacturer();
		response = sendRequestToUpdateItem();
		assertItemManufacturer();
	}

	@Test
	public void testWhenSentUpdateItemStockRequestThenItemStockUpdated() throws Exception {
		itemTestHelper.insertData();
		buildFormToUpdateItemStock();
		response = sendRequestToUpdateItem();
		assertItemStock();
	}


	private void buildFormToUpdateItemName() throws Exception {
		updateItemTestForm = new UpdateItemTestForm();
		updateItemTestForm.setName("Gold Scissors");
	}

	private void buildFormToUpdateItemPrice(){
		updateItemTestForm = new UpdateItemTestForm();
		updateItemTestForm.setPrice(13.3f);
	}

	private void buildFormToUpdateItemManufacturer(){
		updateItemTestForm = new UpdateItemTestForm();
		updateItemTestForm.setManufacturer("New Manufacturer 1");
	}

	private void buildFormToUpdateItemStock(){
		updateItemTestForm = new UpdateItemTestForm();
		updateItemTestForm.setStock(200);
	}

	private ResultActions sendRequestToUpdateItem() throws Exception{
		return mvc.perform(buildRequestToUpdateItem());
	}

	private MockHttpServletRequestBuilder buildRequestToUpdateItem() throws Exception{
		long id = itemTestHelper.getItemId(1);
		return addToken(
				MultipartPutRequest.builder(
								itemTestHelper.getRouteWithId(),
								String.valueOf(id)
						)
						.content(mapper.writeValueAsString(updateItemTestForm))
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON)
		);
	}

	private void assertResponseOk() throws Exception {
		response.andExpect(status().isOk());
	}

	private void assertItemName(){
		assertEquals("Gold Scissors", itemTestHelper.getItemName(1));
	}
	private void assertItemPrice(){
		assertEquals(13.3f, itemTestHelper.getItemPrice(1));
	}
	private void assertItemManufacturer(){
		assertEquals("New Manufacturer 1", itemTestHelper.getItemManufacturer(1));
	}

	private void assertItemStock(){
		assertEquals(200, itemTestHelper.getItemStock(1));
	}

	private void assertItemPriceHistoryCount(){
		assertEquals(2, itemTestHelper.countItemPriceHistories(1));
	}

}
