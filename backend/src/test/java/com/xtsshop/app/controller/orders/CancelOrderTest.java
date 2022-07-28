package com.xtsshop.app.controller.orders;

import com.xtsshop.app.TestCase;
import com.xtsshop.app.db.entities.OrderStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class CancelOrderTest extends TestCase {

    @Autowired
    private CancelOrderTestHelper helper;

    @Test
    public void testWhenCancelOrderRequestIsSentThenOrderIsCanceled() throws Exception {
        helper.insertData();
        helper.insertOrderForAllNormalUsers();
        sendRequestToCancelOrder();
        assertOrderIsCanceled();
    }

    @Test
    public void testWhenCancelOrderRequestIsSentThenAppleStockIsRestored() throws Exception{
        helper.insertData();
        helper.insertOrderForAllNormalUsers();
        sendRequestToCancelOrder();
        assertAppleStockIsRestored();
    }

    @Test
    public void testWhenCancelOrderRequestIsSentThenOrangeStockIsRestored() throws Exception{
        helper.insertData();
        helper.insertOrderForAllNormalUsers();
        sendRequestToCancelOrder();
        assertOrangeStockIsRestored();
    }

    @Test
    public void testWhenCancelOtherUserAsNormalUserRequestSentThenOrderIsNotCanceled() throws Exception {
        helper.insertData();
        helper.insertOrderForAllNormalUsers();
        signInAsOtherUser();
        sendRequestToCancelOrder();
        assertOrderIsTheSameState();
    }

    @Test
    public void testWhenCancelOtherUserAsNormalUserRequestSentThenAppleStockIsUnchanged() throws Exception {
        helper.insertData();
        helper.insertOrderForAllNormalUsers();
        signInAsOtherUser();
        sendRequestToCancelOrder();
        assertAppleStockIsUnchanged();
    }

    @Test
    public void testWhenCancelOtherUserAsNormalUserRequestSentThenOrangeStockIsUnchanged() throws Exception {
        helper.insertData();
        helper.insertOrderForAllNormalUsers();
        signInAsOtherUser();
        sendRequestToCancelOrder();
        assertOrangeStockIsUnchanged();
    }

    @Test
    public void testWhenCancelOrderRequestAsAdminSentThenOrderIsCanceled() throws Exception{
        helper.insertData();
        helper.insertOrderForAllNormalUsers();
        signInAsAdmin();
        sendRequestToCancelOrder();
        assertOrderIsCanceled();
    }

    @Test
    public void testWhenCancelOrderAsAdminRequestIsSentThenAppleStockIsRestored() throws Exception{
        helper.insertData();
        helper.insertOrderForAllNormalUsers();
        sendRequestToCancelOrder();
        assertAppleStockIsRestored();
    }

    @Test
    public void testWhenCancelOrderAsAdminRequestIsSentThenOrangeStockIsRestored() throws Exception{
        helper.insertData();
        helper.insertOrderForAllNormalUsers();
        sendRequestToCancelOrder();
        assertOrangeStockIsRestored();
    }

    private void sendRequestToCancelOrder() throws Exception{
        MockHttpServletRequestBuilder request = buildRequestToCancelOrder();
        mvc.perform(request)
                .andDo(print());
    }

    private MockHttpServletRequestBuilder buildRequestToCancelOrder() throws Exception {
        long orderId = helper.getIdOfOrderToCancel();
        return requestBuilder(HttpMethod.PATCH, helper.getCancelOrderRoute(), String.valueOf(orderId));
    }

    private void assertOrderIsCanceled(){
        OrderStatus orderStatus = helper.getLatestOrder().getStatus();
        assertEquals(orderStatus, OrderStatus.CANCELED);
    }

    private void assertOrderIsTheSameState(){
        OrderStatus latestOrderStatus = helper.getLatestOrder().getStatus();
        assertEquals(latestOrderStatus, OrderStatus.WAITING_PAYMENT);
    }

    private void assertAppleStockIsRestored(){
        int stock = helper.getAppleStock();
        assertEquals(102, stock);
    }

    private void assertAppleStockIsUnchanged(){
        int appleStock = helper.getAppleStock();
        assertEquals(100, appleStock);
    }

    private void assertOrangeStockIsRestored(){
        int stock = helper.getOrangeStock();
        assertEquals(108, stock);
    }

    private void assertOrangeStockIsUnchanged(){
        int orangeStock = helper.getOrangeStock();
        assertEquals(101, orangeStock);
    }

    private void signInAsOtherUser(){
        setUserCredential("mario123", "123");
    }

    private void signInAsAdmin(){
        setUserCredential("ken123", "123");
    }

}
