package com.xtsshop.app.features.categories;

import com.xtsshop.app.TestCase;
import com.xtsshop.app.form.categories.UpdateCategoryForm;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class UpdateTest extends TestCase {
	private String route = "/api/categories";

	@Autowired
	private CategoryUpdateTestHelper categoryUpdateTestHelper;

	private ResultActions response;

	@Test
	public void testWhenUpdateCategoryNameRequestSentThenResponseOk() throws Exception {
		categoryUpdateTestHelper.insertData();
		response = sendRequestToUpdateCategoryName();
		assertResponseOk();
	}

	@Test
	public void testWhenUpdateCategoryNameRequestSentThenCategoryNameChanged() throws Exception {
		categoryUpdateTestHelper.insertData();
		response = sendRequestToUpdateCategoryName();
		assertCategoryNameChanged();
	}

	private ResultActions sendRequestToUpdateCategoryName() throws Exception{
		return mvc
				.perform(buildRequestToUpdateCategoryName())
				.andDo(print());
	}

	private MockHttpServletRequestBuilder buildRequestToUpdateCategoryName() throws Exception{
		long firstCategoryId = categoryUpdateTestHelper.getCategoryId(1);
		return requestBuilder(HttpMethod.PUT, route + "/{id}", String.valueOf(firstCategoryId))
				.content(mapper.writeValueAsString(new UpdateCategoryForm("Container", null)))
				.contentType(MediaType.APPLICATION_JSON);
	}

	private void assertCategoryNameChanged(){
		assertEquals("Container", categoryUpdateTestHelper.getCategoryName(1));
	}

	@Test
	public void testWhenChangeParentCategoryRequestSentThenResponseOk() throws Exception {
		categoryUpdateTestHelper.insertData();
		response = sendRequestToChangeParentOfCategory();
		assertResponseOk();
	}

	@Test
	public void testWhenChangeParentCategoryRequestSentThenParentCategoryChanged() throws Exception{
		categoryUpdateTestHelper.insertData();
		response = sendRequestToChangeParentOfCategory();
		assertCategoryParentIsChanged();
	}

	private ResultActions sendRequestToChangeParentOfCategory() throws Exception{
		return mvc
				.perform(buildRequestToChangeParentOfCategory())
				.andDo(print());
	}

	private MockHttpServletRequestBuilder buildRequestToChangeParentOfCategory() throws Exception{
		long categoryId = categoryUpdateTestHelper.getCategoryId(2);
		return requestBuilder(HttpMethod.PUT, route + "/{id}", String.valueOf(categoryId))
				.content(mapper.writeValueAsString(new UpdateCategoryForm(null, 2L)))
				.contentType(MediaType.APPLICATION_JSON);
	}

	private void assertCategoryParentIsChanged() throws Exception{
		assertNotNull(categoryUpdateTestHelper.getCategoryParent(2));
		assertEquals("tools", categoryUpdateTestHelper.getCategoryParentName(2));
	}

	private void assertResponseOk() throws Exception{
		response
				.andExpect(status().isOk());
	}
}
