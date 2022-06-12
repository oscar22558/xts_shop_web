package com.xtsshop.app.controller.categories.items;

import com.xtsshop.app.helpers.MediaType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class ListAllTest {
    @Autowired
    private ListAllTestHelper util;

    @Test
    void testCaseResponseOk() throws Exception{
        util.insertData();
        ResultActions response = util.getMockMvc().perform(buildRequest()).andDo(print());
        assertResponseOk(response);
    }

    @Test
    void testCaseResponseHasCorrectSize() throws Exception{
        util.insertData();
        ResultActions response = util.getMockMvc().perform(buildRequest()).andDo(print());
        assertResponseSize(response, 2);
        assertResponseContainAppleAndOrange(response);
    }

    @Test
    void testCaseResponseContainCorrectItems() throws Exception{
        util.insertData();
        ResultActions response = util.getMockMvc().perform(buildRequest()).andDo(print());
        assertResponseContainAppleAndOrange(response);
    }

    private MockHttpServletRequestBuilder buildRequest(){
        long categoryId = util.getCategoryIdByName("food");
        return get(util.getIndexRoute(categoryId));
    }

    private void assertResponseOk(ResultActions response) throws Exception{
        response.andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_HALJSON));
    }

    private void assertResponseSize(ResultActions response, int size) throws Exception {
        response.andExpect(jsonPath("$._embedded.itemModelList", hasSize(2)));
    }

    private void assertResponseContainAppleAndOrange(ResultActions response) throws Exception {
        response
                .andExpect(jsonPath("$._embedded.itemModelList.[0].name", is("apple")))
                .andExpect(jsonPath("$._embedded.itemModelList.[0].price.value", is(12.2)))
                .andExpect(jsonPath("$._embedded.itemModelList.[0].manufacturer", is("manufacturer 1")))
                .andExpect(jsonPath("$._embedded.itemModelList.[1].name", is("orange")))
                .andExpect(jsonPath("$._embedded.itemModelList.[1].price.value", is(23.2)))
                .andExpect(jsonPath("$._embedded.itemModelList.[1].manufacturer", is("manufacturer 2")));
    }

    @Test
    public void testCaseItemsAscSortedByPrice() throws Exception {

        util.insertData();
        ResultActions response = util.getMockMvc().perform(buildRequestWithPriceAscSorting()).andDo(print());
        assertItemsAscSortedByPrice(response);
    }

    private MockHttpServletRequestBuilder buildRequestWithPriceAscSorting(){
        long categoryId = util.getCategoryIdByName("food");
        return get(util.getIndexRoute(categoryId))
                .param("sorting", "price")
                .param("sortingDirection", "asc");
    }

    private void assertItemsAscSortedByPrice(ResultActions response) throws Exception{
        response
                .andExpect(jsonPath("$._embedded.itemModelList.[0].name", is("apple")))
                .andExpect(jsonPath("$._embedded.itemModelList.[1].name", is("orange")))
                .andExpect(jsonPath("$._embedded.itemModelList.[2].name", is("2M USB 3.0 data cable")));

    }

    @Test
    public void testCaseItemsDescSortedByPrice() throws Exception {
        util.insertData();
        ResultActions response = util.getMockMvc().perform(buildRequestWithPriceDescSorting()).andDo(print());
        assertItemsDescSortedByPrice(response);
    }

    private MockHttpServletRequestBuilder buildRequestWithPriceDescSorting(){
        long categoryId = util.getCategoryIdByName("food");
        return get(util.getIndexRoute(categoryId))
                .param("sorting", "price")
                .param("sortingDirection", "asc");
    }

    private void assertItemsDescSortedByPrice(ResultActions response) throws Exception{
        response
                .andExpect(jsonPath("$._embedded.itemModelList.[2].name", is("apple")))
                .andExpect(jsonPath("$._embedded.itemModelList.[1].name", is("orange")))
                .andExpect(jsonPath("$._embedded.itemModelList.[0].name", is("2M USB 3.0 data cable")));
    }
}
