package com.xtsshop.app.http.orders;

import com.xtsshop.app.db.entities.Order;
import com.xtsshop.app.db.entities.OrderStatus;
import com.xtsshop.app.db.repositories.*;
import com.xtsshop.app.http.TestCase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;

import javax.transaction.Transactional;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class GetTest extends OrdersTest {
    @Test
    public void test() throws Exception {
        util.insertData();
        Order order = util.getOrderOfUser();
        //use normal user
        setUserCredential("marry123", "123");
        mvc.perform(requestBuilder(HttpMethod.GET, util.getGetRequestRoute(), order.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.user.username", is("marry123")))
                .andExpect(jsonPath("$.items", hasSize(3)))
                .andExpect(jsonPath("$.items.[0].item.name", is("apple")))
                .andExpect(jsonPath("$.items.[0].item.price.value", is(12.2)))
                .andExpect(jsonPath("$.items.[0].quantity", is(2)))
                .andExpect(jsonPath("$.items.[1].item.name", is("orange")))
                .andExpect(jsonPath("$.items.[1].item.price.value", is(25.5)))
                .andExpect(jsonPath("$.items.[1].quantity", is(7)));
    }

    @Test
    public void testOrderWithPayment() throws Exception {
        util.insertDataForTestOrderWithPayment();

        Order order = util.getOrderOfUser();
        //use normal user
        setUserCredential("marry123", "123");
        mvc.perform(requestBuilder(HttpMethod.GET, util.getGetRequestRoute(), order.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.orderStatus", is(OrderStatus.PAID.name())))
                .andExpect(jsonPath("$.user.username", is("marry123")))
                .andExpect(jsonPath("$.items", hasSize(3)))
                .andExpect(jsonPath("$.items.[0].item.name", is("apple")))
                .andExpect(jsonPath("$.items.[0].item.price.value", is(12.2)))
                .andExpect(jsonPath("$.items.[0].quantity", is(4)))
                .andExpect(jsonPath("$.items.[1].item.name", is("orange")))
                .andExpect(jsonPath("$.items.[1].item.price.value", is(23.2)))
                .andExpect(jsonPath("$.items.[1].quantity", is(2)));
    }
    @Test
    public void testAccessOrderOfOtherUser() throws Exception {
        util.insertData();
        Order order = util.getOrderOfUser();
        //use normal user
        setUserCredential("mario123", "123");
        mvc.perform(requestBuilder(HttpMethod.GET, util.getGetRequestRoute(), order.getId()))
                .andDo(print())
                .andExpect(status().isForbidden());
    }

    @Test
    public void testAccessOrderAsAdmin() throws Exception {
        util.insertData();
        Order order = util.getOrderOfUser();
        //use normal user
        setUserCredential("ken123", "123");
        mvc.perform(requestBuilder(HttpMethod.GET, util.getGetRequestRoute(), order.getId()))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
