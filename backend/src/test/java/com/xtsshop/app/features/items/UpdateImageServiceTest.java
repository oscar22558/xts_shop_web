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
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class UpdateImageServiceTest extends TestCase {
	@Autowired
	private ItemTestHelper itemTestHelper;

	private ResultActions response;
	private String oldImagePath;

	@Test
	public void testWhenUpdateItemImageRequestSentThenResponseCreated() throws Exception{
		itemTestHelper.insertData();
		response = sendRequestToUpdateItemImage();
		assertResponseCreated();
	}

	@Test
	public void testWhenUpdateItemImageRequestSentThenNewFileExistInTheStorage() throws Exception{
		itemTestHelper.insertData();
		response = sendRequestToUpdateItemImage();
		assertNewFileExistsInStorage();
	}

	@Test
	public void testWhenUpdateItemImageRequestSentThenOldFileIsDeleted() throws Exception{
		itemTestHelper.insertData();
		setOldImagePath();
		response = sendRequestToUpdateItemImage();
		assertOldFileDeletedFromStorage();
	}

	@Test
	public void testWhenUpdateItemImageRequestSentThenFileIsRecordedInDatabase() throws Exception{
		itemTestHelper.insertData();
		response = sendRequestToUpdateItemImage();
		assertFileIsRecordedInDatabase();
	}

	private MockMultipartFile buildImgFile(){
		return new MockMultipartFile(
				"image",
				"hello.png",
				MediaType.IMAGE_PNG_VALUE,
				"Hello, World!".getBytes()
		);
	}

	private ResultActions sendRequestToUpdateItemImage() throws Exception{
		return mvc.perform(buildRequestToUpdateItemImage()).andDo(print());
	}

	private MockHttpServletRequestBuilder buildRequestToUpdateItemImage() throws Exception{
		long id = itemTestHelper.getItemId(1);
		return addToken(
				multipart(itemTestHelper.getUpdateImageRoute(), id)
						.file(buildImgFile())
		);
	}

	private void assertResponseCreated() throws Exception{
		response.andExpect(status().isCreated());
	}

	private void assertNewFileExistsInStorage() throws Exception {
		String itemImagePath = itemTestHelper.getItemImagePath(1);
		assertTrue(Paths.get(itemImagePath).toFile().exists());
	}

	private void setOldImagePath(){
		oldImagePath = itemTestHelper.getItemImagePath(1);
	}

	private void assertOldFileDeletedFromStorage() throws Exception {
		assertFalse(Paths.get(oldImagePath).toFile().exists());
	}

	private void assertFileIsRecordedInDatabase(){
		String itemImagePath = itemTestHelper.getItemImagePath(1);
		assertNotNull(itemImagePath);
	}
}
