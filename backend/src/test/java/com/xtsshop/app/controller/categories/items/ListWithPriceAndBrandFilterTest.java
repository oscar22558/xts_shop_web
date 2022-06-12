package com.xtsshop.app.controller.categories.items;

import com.xtsshop.app.helpers.MediaType;
import com.xtsshop.app.TestCase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class ListWithPriceAndBrandFilterTest extends TestCase {
    @Autowired
    private ListWithPriceAndBrandFilterTestHelper util;

    @Test
    public void testCaseWithBrandAndPriceFilter() throws Exception{
        util.insertData();
        ResultActions response = util.getMockMvc()
                .perform(buildRequestWithBrandAndPriceFilter())
                .andDo(print());
        assertResponseIsEmpty(response);
    }
    private MockHttpServletRequestBuilder buildRequestWithBrandAndPriceFilter(){
        return get(util.getRoute())
                .queryParam("categoryIds[]", util.getFoodCategoryId()+"")
                .queryParam("brandIds[]", util.getBrandId()+"")
                .queryParam("maxPrice", 5f+"")
                .queryParam("minPrice", 12f+"");
    }
    private void assertResponseIsEmpty(ResultActions response) throws Exception {
       response.andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_HALJSON))
                .andExpect(jsonPath("$", anEmptyMap()));
    }

    @Test
    public void testCaseWithBrandFilter() throws Exception{
        util.insertData();
        ResultActions response = util.getMockMvc()
                .perform(buildRequestWithBrandFilter())
                .andDo(print());
        assertResponseOk(response);
        assertResponseHasSize(response, 1);
        assertResponseContainApple(response);
    }

    private MockHttpServletRequestBuilder buildRequestWithBrandFilter(){
       return get(util.getRoute())
               .queryParam("categoryIds[]", util.getFoodCategoryId()+"")
               .queryParam("brandIds[]", util.getBrandId()+"");
    }


    @Test
    public void testCaseWithPriceFilter() throws Exception{
        util.insertData();
        ResultActions response = util.getMockMvc()
                .perform(buildRequestWithPriceFilter())
                .andDo(print());
        assertResponseOk(response);
        assertResponseHasSize(response, 1);
        assertResponseContainOrange(response);
    }

    private MockHttpServletRequestBuilder buildRequestWithPriceFilter() throws Exception{
       return get(util.getRoute())
               .queryParam("categoryIds[]", util.getFoodCategoryId()+"")
               .queryParam("maxPrice", 23.2f+"")
               .queryParam("minPrice", 23.1f+"");
    }

    private void assertResponseContainOrange(ResultActions response) throws  Exception{
       response.andExpect(jsonPath("$._embedded.itemModelList.[0].name", is("orange")))
               .andExpect(jsonPath("$._embedded.itemModelList.[0].price.value", is(23.2)))
               .andExpect(jsonPath("$._embedded.itemModelList.[0].manufacturer", is("manufacturer 2")));
    }
    private void assertResponseOk(ResultActions response) throws Exception {
        response.andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_HALJSON));
    }

    @Test
    public void testCaseWithLargerCategorySet() throws Exception{
        util.insertData();
        ResultActions response = util.getMockMvc().perform(buildRequestWithLargerCategorySet());
        assertResponseContainItemFromFood(response);
        assertResponseHasSize(response, 4);
        assertResponseContainItemFromMobileDevicePeripheral(response);
    }
    private MockHttpServletRequestBuilder buildRequestWithLargerCategorySet(){
        return get(util.getRoute())
                .queryParam("categoryIds[]", util.getMobileDevicePeripheralId()+"")
                .queryParam("categoryIds[]", util.getFoodCategoryId()+"");
    }
    private void assertResponseContainItemFromMobileDevicePeripheral(ResultActions response) throws Exception {
        assertResponseContainStand(response);
    }
    private void assertResponseContainStand(ResultActions response) throws Exception{
        response.andExpect(jsonPath("$._embedded.itemModelList.[3].name", is("ABC stand")))
                .andExpect(jsonPath("$._embedded.itemModelList.[3].price.value", is(55.2)))
                .andExpect(jsonPath("$._embedded.itemModelList.[3].manufacturer", is("manufacturer 2")));
    }

    private void assertResponseContainItemFromFood(ResultActions response) throws Exception {
        assertResponseContainApple(response);
    }
    private void assertResponseContainApple(ResultActions response) throws Exception {
        response.andExpect(jsonPath("$._embedded.itemModelList.[0].name", is("apple")))
                .andExpect(jsonPath("$._embedded.itemModelList.[0].price.value", is(12.2)))
                .andExpect(jsonPath("$._embedded.itemModelList.[0].manufacturer", is("manufacturer 1")));
    }

    private void assertResponseHasSize(ResultActions response, int size) throws Exception {
        response.andExpect(jsonPath("$._embedded.itemModelList", hasSize(size)));
    }
}
