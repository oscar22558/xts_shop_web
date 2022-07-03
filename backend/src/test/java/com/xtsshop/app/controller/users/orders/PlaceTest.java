package com.xtsshop.app.controller.users.orders;

import com.xtsshop.app.db.entities.Order;
import com.xtsshop.app.db.entities.OrderStatus;
import com.xtsshop.app.db.repositories.ItemRepository;
import com.xtsshop.app.db.repositories.OrderRepository;
import com.xtsshop.app.db.repositories.RoleRepository;
import com.xtsshop.app.db.repositories.UserRepository;
import com.xtsshop.app.TestCase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class PlaceTest extends TestCase {

    @Autowired
    private UserOrderTestHelper userOrderTestHelper;

    @Test
    @Transactional
    public void testWithPayment() throws Exception {
        userOrderTestHelper.insertData();
        setUserCredential(getUserUsername(), getPassword());
        userOrderTestHelper.updatePriceOfItem();
        mvc.perform(requestBuilder(HttpMethod.POST, userOrderTestHelper.getRoute(), "marry123")
                        .content(mapper.writeValueAsBytes(userOrderTestHelper.buildCreatFormWithPayment()))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isCreated())
                .andDo(print());
        Order order = userOrderTestHelper.getLatestOrder().orElseThrow();
        assertEquals((long) order.getOrderedItems().size(), 2);
        assertEquals(order.getOrderedItems().get(0).getItem().getName(), "apple");
        assertEquals(order.getOrderedItems().get(0).getOrderPrice().getValue(), 12.2f);
        assertEquals(order.getOrderedItems().get(0).getItem().getStock(), 90);
        assertEquals(order.getOrderedItems().get(1).getItem().getName(), "orange");
        assertEquals(order.getOrderedItems().get(1).getOrderPrice().getValue(), 25.5f);
        assertEquals(order.getOrderedItems().get(1).getItem().getStock(), 90);
        assertEquals(order.getStatus(), OrderStatus.PAID);
        assertEquals(order.getPayment().getPaidTotal(), 402.5f);
        assertEquals(order.getUser().getUsername(), "marry123");
    }

    @Test
    @Transactional
    public void testWithoutPayment() throws Exception {
        userOrderTestHelper.insertData();
        setUserCredential("marry123", "123");
        mvc.perform(requestBuilder(HttpMethod.POST, userOrderTestHelper.getRoute(), "marry123")
                        .content(mapper.writeValueAsBytes(userOrderTestHelper.buildCreatFormWithoutPayment()))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isCreated())
                .andDo(print());

        Order order = userOrderTestHelper.getLatestOrder().orElseThrow();
        assertEquals((long) order.getOrderedItems().size(), 2);
        assertNull(order.getPayment());
        assertEquals(order.getStatus(), OrderStatus.WAITING_PAYMENT);
        assertEquals(order.getOrderedItems().get(0).getItem().getName(), "apple");
        assertNull(order.getOrderedItems().get(0).getOrderPrice());
        assertEquals(order.getOrderedItems().get(1).getItem().getName(), "orange");
        assertNull(order.getOrderedItems().get(1).getOrderPrice());
        assertEquals(order.getUser().getUsername(), "marry123");
    }
    @Test
    @Transactional
    public void testWithPaymentAsAdmin() throws Exception {
        mvc.perform(requestBuilder(HttpMethod.POST, userOrderTestHelper.getRoute(), "marry123")
                        .content(mapper.writeValueAsBytes(userOrderTestHelper.buildCreatFormWithPayment()))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isForbidden())
                .andDo(print());
    }
    @Test
    @Transactional
    public void testWithPaymentAsOtherUser() throws Exception {
        userOrderTestHelper.insertNewUser();
        setUserCredential("mario123", "123");
        mvc.perform(requestBuilder(HttpMethod.POST, userOrderTestHelper.getRoute(), "marry123")
                        .content(mapper.writeValueAsBytes(userOrderTestHelper.buildCreatFormWithPayment()))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isForbidden())
                .andDo(print());
    }
}
