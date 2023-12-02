package com.xtsshop.app.features.categories.items;

import com.xtsshop.app.DependencyTestConfig;
import com.xtsshop.app.LoadDatabaseTestConfig;
import com.xtsshop.app.features.orders.ListUserOrderTestHelper;
import com.xtsshop.app.features.orders.data.FakeNewUserDataSet;
import com.xtsshop.app.features.orders.data.FakeOrderDataSetBuilder;
import com.xtsshop.app.features.orders.data.FakeOrderedItemDataSet;
import com.xtsshop.app.helpers.MediaType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Import({
        LoadDatabaseTestConfig.class,
        DependencyTestConfig.class,
        ListAllCategoryItemTestHelper.class
})
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@ActiveProfiles("dev")
public class ListAllCategoryItemTest {
    @Autowired
    private ListAllCategoryItemTestHelper helper;

    @Test
    void testCaseResponseOk() throws Exception{
        helper.insertData();
        ResultActions response = helper.getMockMvc().perform(buildRequest()).andDo(print());
        assertResponseOk(response);
    }

    @Test
    void testCaseResponseHasCorrectSize() throws Exception{
        helper.insertData();
        ResultActions response = helper.getMockMvc().perform(buildRequest()).andDo(print());
        assertResponseSize(response, 2);
        assertResponseContainAppleAndOrange(response);
    }

    @Test
    void testCaseResponseContainCorrectItems() throws Exception{
        helper.insertData();
        ResultActions response = helper.getMockMvc().perform(buildRequest()).andDo(print());
        assertResponseContainAppleAndOrange(response);
    }

    private MockHttpServletRequestBuilder buildRequest(){
        long categoryId = helper.getCategoryIdByName("food");
        return get(helper.getIndexRoute(categoryId));
    }

    private void assertResponseOk(ResultActions response) throws Exception{
        response.andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_HALJSON));
    }

    private void assertResponseSize(ResultActions response, int size) throws Exception {
        response.andExpect(jsonPath("$._embedded.itemRepresentationModelList", hasSize(2)));
    }

    private void assertResponseContainAppleAndOrange(ResultActions response) throws Exception {
        response
                .andExpect(jsonPath("$._embedded.itemRepresentationModelList.[0].name", is("apple")))
                .andExpect(jsonPath("$._embedded.itemRepresentationModelList.[0].price.value", is(12.2)))
                .andExpect(jsonPath("$._embedded.itemRepresentationModelList.[0].manufacturer", is("manufacturer 1")))
                .andExpect(jsonPath("$._embedded.itemRepresentationModelList.[1].name", is("orange")))
                .andExpect(jsonPath("$._embedded.itemRepresentationModelList.[1].price.value", is(23.2)))
                .andExpect(jsonPath("$._embedded.itemRepresentationModelList.[1].manufacturer", is("manufacturer 2")));
    }

    @Test
    public void testCaseItemsAscSortedByPrice() throws Exception {

        helper.insertData();
        ResultActions response = helper.getMockMvc().perform(buildRequestWithPriceAscSorting()).andDo(print());
        assertItemsAscSortedByPrice(response);
    }

    private MockHttpServletRequestBuilder buildRequestWithPriceAscSorting(){
        long categoryId = helper.getCategoryIdByName("food");
        return get(helper.getIndexRoute(categoryId))
                .param("sorting", "price")
                .param("sortingDirection", "asc");
    }

    private void assertItemsAscSortedByPrice(ResultActions response) throws Exception{
        response
                .andExpect(jsonPath("$._embedded.itemRepresentationModelList.[0].name", is("apple")))
                .andExpect(jsonPath("$._embedded.itemRepresentationModelList.[1].name", is("orange")));
    }

    @Test
    public void testCaseItemsDescSortedByPrice() throws Exception {
        helper.insertData();
        ResultActions response = helper.getMockMvc().perform(buildRequestWithPriceDescSorting()).andDo(print());
        assertItemsDescSortedByPrice(response);
    }

    private MockHttpServletRequestBuilder buildRequestWithPriceDescSorting(){
        long categoryId = helper.getCategoryIdByName("food");
        return get(helper.getIndexRoute(categoryId))
                .param("sorting", "price")
                .param("sortingDirection", "desc");
    }

    private void assertItemsDescSortedByPrice(ResultActions response) throws Exception{
        response
                .andExpect(jsonPath("$._embedded.itemRepresentationModelList.[1].name", is("apple")))
                .andExpect(jsonPath("$._embedded.itemRepresentationModelList.[0].name", is("orange")));
    }
}
