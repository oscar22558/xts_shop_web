package com.xtsshop.app.http.orders;

import com.xtsshop.app.db.entities.AppUser;
import com.xtsshop.app.db.entities.Item;
import com.xtsshop.app.db.entities.Order;
import com.xtsshop.app.db.entities.OrderStatus;
import com.xtsshop.app.db.repositories.ItemRepository;
import com.xtsshop.app.db.repositories.OrderRepository;
import com.xtsshop.app.db.repositories.RoleRepository;
import com.xtsshop.app.db.repositories.UserRepository;
import com.xtsshop.app.http.TestCase;
import com.xtsshop.app.http.users.orders.Util;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.test.annotation.DirtiesContext;

import javax.transaction.Transactional;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class CancelTest extends OrdersTest {
    @Test
    @Transactional
    public void test() throws Exception {
        util.insertOrderForUser(util.getUser());
        long orderId = util.getLatestOrder().orElseThrow(()->new Exception("No order")).getId();
        mvc.perform(requestBuilder(HttpMethod.PATCH, util.getGetRequestRoute()+"/cancel", String.valueOf(orderId)))
                .andDo(print())
                .andExpect(status().isOk())
        ;
        Order order = util.getLatestOrder().orElseThrow(()->new Exception("No order"));
        Item orderedItem1 = util.getLatestOrderItemId(0);
        Item orderedItem2 = util.getLatestOrderItemId(1);
        assertEquals(order.getStatus(), OrderStatus.CANCELED);
        assertEquals(102, orderedItem1.getStock());
        assertEquals(108, orderedItem2.getStock());
    }
    @Test
    @Transactional
    public void testCancelOtherUserOrder() throws Exception {
        util.insertOrderForNewUser(util.insertNewUser());
        util.insertOrderForUser(util.getUser());
        long orderId = util.getLatestOrder().orElseThrow(()->new Exception("No order")).getId();
        setUserCredential("mario123", "123");
        mvc.perform(requestBuilder(HttpMethod.PATCH, util.getGetRequestRoute()+"/cancel", String.valueOf(orderId)))
                .andDo(print())
                .andExpect(status().isForbidden())
        ;
        Order order = util.getLatestOrder().orElseThrow(()->new Exception("No order"));
        Item orderedItem1 = util.getLatestOrderItemId(0);
        Item orderedItem2 = util.getLatestOrderItemId(1);
        assertEquals(order.getStatus(), OrderStatus.WAITING_PAYMENT);
        assertEquals(100, orderedItem1.getStock());
        assertEquals(101, orderedItem2.getStock());
    }

    @Test
    @Transactional
    public void testCancelOrderAsAdmin() throws Exception {
        util.insertOrderForNewUser(util.insertNewUser());
        util.insertOrderForUser(util.getUser());
        long orderId = util.getLatestOrder().orElseThrow(()->new Exception("No order")).getId();
        mvc.perform(requestBuilder(HttpMethod.PATCH, util.getGetRequestRoute()+"/cancel", String.valueOf(orderId)))
                .andDo(print())
                .andExpect(status().isOk())
        ;
        Order order = util.getLatestOrder().orElseThrow(()->new Exception("No order"));
        Item orderedItem1 = util.getLatestOrderItemId(0);
        Item orderedItem2 = util.getLatestOrderItemId(1);
        assertEquals(order.getStatus(), OrderStatus.CANCELED);
        assertEquals(102, orderedItem1.getStock());
        assertEquals(108, orderedItem2.getStock());
    }
}
