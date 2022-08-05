package com.xtsshop.app.features.categories.items;

import com.xtsshop.app.TestCase;
import com.xtsshop.app.helpers.MediaType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@Import({
        ListWithPriceAndBrandFilterTestHelper.class
})
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
        return get(util.getRoute(util.getFoodCategoryId()))
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
       return get(util.getRoute(util.getFoodCategoryId()))
               .queryParam("brandIds[]", util.getBrandId()+"")
               .queryParam("minPrice", ""+0)
               .queryParam("maxPrice", ""+10000);
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
       return get(util.getRoute(util.getFoodCategoryId()))
               .queryParam("maxPrice", 23.2f+"")
               .queryParam("minPrice", 23.1f+"");
    }

    private void assertResponseContainOrange(ResultActions response) throws  Exception{
       response.andExpect(jsonPath("$._embedded.itemRepresentationModelList.[0].name", is("orange")))
               .andExpect(jsonPath("$._embedded.itemRepresentationModelList.[0].price.value", is(23.2)))
               .andExpect(jsonPath("$._embedded.itemRepresentationModelList.[0].manufacturer", is("manufacturer 2")));
    }
    private void assertResponseOk(ResultActions response) throws Exception {
        response.andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_HALJSON));
    }

    private void assertResponseContainApple(ResultActions response) throws Exception {
        response.andExpect(jsonPath("$._embedded.itemRepresentationModelList.[0].name", is("apple")))
                .andExpect(jsonPath("$._embedded.itemRepresentationModelList.[0].price.value", is(12.2)))
                .andExpect(jsonPath("$._embedded.itemRepresentationModelList.[0].manufacturer", is("manufacturer 1")));
    }

    private void assertResponseHasSize(ResultActions response, int size) throws Exception {
        response.andExpect(jsonPath("$._embedded.itemRepresentationModelList", hasSize(size)));
    }
}
