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
import org.springframework.http.HttpMethod;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Import({
		ItemTestHelper.class
})
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class DeleteTest extends TestCase {

	private String route = "/api/items/{id}";

	@Autowired
	private ItemTestHelper itemTestHelper;

	@Test
	void testDeleteItemResponseOk() throws Exception {
		itemTestHelper.insertData();
		ResultActions response = sendMockRequest();
		assertResponseIsOk(response);
	}

	@Test
	public void testItemIsDeleted() throws Exception{
		itemTestHelper.insertData();
		sendMockRequest();
		assertOneItemDelete();
	}

	public ResultActions sendMockRequest() throws Exception{
		return mvc.perform(buildRequest()).andDo(print());
	}

	public MockHttpServletRequestBuilder buildRequest() throws Exception{
		return requestBuilder(HttpMethod.DELETE, route, itemTestHelper.getItemIdStr(0));
	}

	public void assertResponseIsOk(ResultActions response) throws Exception{
		response.andExpect(status().isOk());

	}
	public void assertOneItemDelete() {
		assertEquals(3, itemTestHelper.getRepository().findAll().size());
	}
}
