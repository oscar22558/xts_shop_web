package com.xtsshop.app.features.orders;

import com.xtsshop.app.TestCase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class GetTest extends TestCase {
    @Autowired
    private GetTestHelper helper;

    private ResultActions response;

    @Test
    public void testWhenGetOrderRequestSentThenResponseOk() throws Exception {
        helper.insertData();
        helper.updateData();
        signInAsNormalUser();
        sendRequest();
        assertResponseOk();
    }

    @Test
    public void testWhenGetOrderRequestSentThenResponseCorrect() throws Exception {
        helper.insertData();
        helper.updateData();
        signInAsNormalUser();
        sendRequest();
        assertResponseItemCount(3);
    }

    @Test
    public void testWhenGetOrderRequestSentThenResponseListsAppleWithPriceAndQuantity() throws Exception {
        helper.insertData();
        helper.updateData();
        signInAsNormalUser();
        sendRequest();
        assertResponseListsAppleWithPriceAndQuantity();
    }

    @Test
    public void testWhenGetOrderRequestSentThenResponseListsOrangeWithPriceAndQuantity() throws Exception {
        helper.insertData();
        helper.updateData();
        signInAsNormalUser();
        sendRequest();
        assertResponseListsOrangeWithPriceAndQuantity();
    }

    public MockHttpServletRequestBuilder buildRequest() throws Exception{
        String route = helper.getRoute();
        long orderId = helper.getIdOfOrderToQuery();
        return requestBuilder(HttpMethod.GET, route, orderId);
    }

    private void sendRequest() throws Exception{
        MockHttpServletRequestBuilder request = buildRequest();
        response = mvc.perform(request);
    }

    private void assertResponseOk() throws Exception{
        response.andExpect(status().isOk());
    }

    private void assertResponseItemCount(int itemCount) throws Exception {
        response
            .andExpect(jsonPath("$.items", hasSize(itemCount)));
    }

    private void assertResponseListsAppleWithPriceAndQuantity() throws Exception {
        response
                .andExpect(jsonPath("$.items.[0].name", is("apple")))
                .andExpect(jsonPath("$.items.[0].price", is(12.2)))
                .andExpect(jsonPath("$.items.[0].quantity", is(2)));
    }

    private void assertResponseListsOrangeWithPriceAndQuantity() throws Exception {
        response
                .andExpect(jsonPath("$.items.[1].name", is("orange")))
                .andExpect(jsonPath("$.items.[1].price", is(25.5)))
                .andExpect(jsonPath("$.items.[1].quantity", is(7)));

    }

//    @Test
//    public void testOrderWithPayment() throws Exception {
//        dataSeed.insertData();
//        orderTestHelper.insertDataForTestOrderWithPayment();
//
//        Order order = orderTestHelper.getOrderOfUser();
//        //use normal user
//        signInAsNormalUser();
//        mvc.perform(requestBuilder(HttpMethod.GET, orderTestHelper.getOneOrderRoute(), order.getId()))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.orderStatus", is(OrderStatus.PAID.name())))
//                .andExpect(jsonPath("$.user.username", is("marry123")))
//                .andExpect(jsonPath("$.items", hasSize(3)))
//                .andExpect(jsonPath("$.items.[0].item.name", is("apple")))
//                .andExpect(jsonPath("$.items.[0].item.price.value", is(12.2)))
//                .andExpect(jsonPath("$.items.[0].quantity", is(4)))
//                .andExpect(jsonPath("$.items.[1].item.name", is("orange")))
//                .andExpect(jsonPath("$.items.[1].item.price.value", is(23.2)))
//                .andExpect(jsonPath("$.items.[1].quantity", is(2)));
//    }
//    @Test
//    public void testAccessOrderOfOtherUser() throws Exception {
//        dataSeed.insertData();
//        orderTestHelper.insertData();
//        Order order = orderTestHelper.getOrderOfUser();
//        //use normal user
//        signInAsNewNormalUser();
//        mvc.perform(requestBuilder(HttpMethod.GET, orderTestHelper.getOneOrderRoute(), order.getId()))
//                .andDo(print())
//                .andExpect(status().isForbidden());
//    }
//
//    @Test
//    public void testAccessOrderAsAdmin() throws Exception {
//        dataSeed.insertData();
//        orderTestHelper.insertData();
//        Order order = orderTestHelper.getOrderOfUser();
//        //use normal user
//        signInAsAdmin();
//        mvc.perform(requestBuilder(HttpMethod.GET, orderTestHelper.getOneOrderRoute(), order.getId()))
//                .andDo(print())
//                .andExpect(status().isOk());
//    }

    private void signInAsNormalUser(){
        setUserCredential("marry123", "123");
    }

    private void signInAsNewNormalUser(){
        setUserCredential("mario123", "123");
    }

    private void signInAsAdmin(){
        setUserCredential("ken123", "123");
    }
}
