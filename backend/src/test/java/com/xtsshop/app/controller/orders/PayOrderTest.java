package com.xtsshop.app.controller.orders;

import com.xtsshop.app.TestCase;
import com.xtsshop.app.db.entities.Order;
import com.xtsshop.app.db.entities.OrderStatus;
import com.xtsshop.app.db.entities.payment.Payment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class PayOrderTest extends TestCase {
    @Autowired
    private PayOrderTestHelper help;

    private ResultActions response;

    @Test
    public void testWhenPayOrderRequestIsSentThenOrderIsPaid() throws Exception{
        help.insertData();
        help.insertOrderForNewUser();
        signInAsNewUser();
        sendRequestToPayOrder();
        assertOrderIsPay();
    }

    @Test
    public void testWhenAdminPayOrderRequestSentThenPaymentIsForbade() throws Exception{
        help.insertData();
        help.insertOrderForNewUser();
        sendRequestToPayOrder();
        assertResponseForbidden();
    }

    @Test
    public void testWhenUserPayOrderForOtherUserRequestSentThenPaymentIsForbade() throws Exception{
        help.insertData();
        help.insertOrderForNewUser();
        signInAsUser();
        sendRequestToPayOrder();
        assertResponseForbidden();
    }

    private void signInAsUser(){
        setUserCredential(getUserUsername(), "123");
    }

    private void signInAsNewUser(){
        setUserCredential("mario123", "123");
    }

    private MockHttpServletRequestBuilder buildRequestToPayOrder() throws Exception {
        String route = help.getRoute();
        long orderId = help.getOrderIdToPay();
        return requestBuilder(HttpMethod.PATCH, route, orderId)
                .content(mapper.writeValueAsString(help.buildPaymentCreateForm()))
                .contentType(MediaType.APPLICATION_JSON);
    }

    private void sendRequestToPayOrder() throws Exception {
        MockHttpServletRequestBuilder request = buildRequestToPayOrder();
        response = mvc.perform(request)
                .andDo(print());
    }

    private void assertResponseForbidden() throws Exception {
        response.andExpect(status().isForbidden());
    }

    private void assertOrderIsPay(){
        Order order = help.getOrderToPay();
        OrderStatus orderStatus = order.getStatus();
        assertEquals(OrderStatus.PAID, orderStatus);

        Payment payment = help.getOrderToPay().getPayment();
        assertNotNull(payment);
    }
}
