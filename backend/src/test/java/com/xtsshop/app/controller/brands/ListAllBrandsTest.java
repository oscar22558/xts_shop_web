package com.xtsshop.app.controller.brands;


import com.xtsshop.app.TestCase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class ListAllBrandsTest extends TestCase {
    @Autowired
    private ListAllBrandsTestHelper helper;

    private ResultActions response;


    @Test
    public void testResponseOk() throws Exception {
        helper.insertData();
        response = sendRequest();
        assertResponseOk();
    }

    @Test
    public void testListBrandCountCorrect() throws Exception {
       helper.insertData();
       response = sendRequest();
       assertResponseBrandsCount(3);
    }

    @Test
    public void testFirstBrandInList() throws Exception {
        helper.insertData();
        response = sendRequest();
        assertFirstBrandName();
    }

    private MockHttpServletRequestBuilder buildRequest() throws Exception {
        return requestBuilder(HttpMethod.GET, helper.getRoute())
                .contentType(MediaType.APPLICATION_JSON);
    }

    private ResultActions sendRequest() throws Exception{
        return mvc.perform(buildRequest()).andDo(print());
    }

    private void assertResponseOk() throws Exception{
        response.andExpect(status().isOk());
    }
    private void assertResponseBrandsCount(int brandCount) throws Exception{
       response.andExpect(jsonPath("$._embedded.brandRepresentationModelList", hasSize(brandCount)));
    }

    private void assertFirstBrandName() throws Exception {
        response.andExpect(jsonPath("$._embedded.brandRepresentationModelList.[0].name", is("Brand 1")));
    }
}
