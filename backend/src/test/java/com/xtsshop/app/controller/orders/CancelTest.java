package com.xtsshop.app.controller.orders;

import com.xtsshop.app.TestCase;
import com.xtsshop.app.db.entities.Item;
import com.xtsshop.app.db.entities.Order;
import com.xtsshop.app.db.entities.OrderStatus;
import com.xtsshop.app.db.repositories.ItemRepository;
import com.xtsshop.app.db.seed.DevelopmentDataSeed;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.test.annotation.DirtiesContext;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class CancelTest extends TestCase {

    @Autowired
    public OrderTestHelper orderTestHelper;
    @Autowired
    private DevelopmentDataSeed dataSeed;

    @Autowired
    private ItemRepository itemRepository;

    @Test
    @Transactional
    public void test() throws Exception {
        dataSeed.insertData();
        assertNotNull(itemRepository.findAll().get(0).getImage());
        orderTestHelper.insertOrderForUser(orderTestHelper.getUser());
        long orderId = orderTestHelper.getLatestOrder().orElseThrow(()->new Exception("No order")).getId();
        mvc.perform(requestBuilder(HttpMethod.PATCH, orderTestHelper.getOneOrderRoute()+"/cancel", String.valueOf(orderId)))
                .andDo(print())
                .andExpect(status().isOk())
        ;
        Order order = orderTestHelper.getLatestOrder().orElseThrow(()->new Exception("No order"));
        Item orderedItem1 = orderTestHelper.getLatestOrderItemId(0);
        Item orderedItem2 = orderTestHelper.getLatestOrderItemId(1);
        assertEquals(order.getStatus(), OrderStatus.CANCELED);
        assertEquals(102, orderedItem1.getStock());
        assertEquals(108, orderedItem2.getStock());
    }
    @Test
    @Transactional
    public void testCancelOtherUserOrder() throws Exception {
        dataSeed.insertData();
        orderTestHelper.insertOrderForNewUser(orderTestHelper.insertNewUser());
        orderTestHelper.insertOrderForUser(orderTestHelper.getUser());
        long orderId = orderTestHelper.getLatestOrder().orElseThrow(()->new Exception("No order")).getId();
        setUserCredential("mario123", "123");
        mvc.perform(requestBuilder(HttpMethod.PATCH, orderTestHelper.getOneOrderRoute()+"/cancel", String.valueOf(orderId)))
                .andDo(print())
                .andExpect(status().isForbidden())
        ;
        Order order = orderTestHelper.getLatestOrder().orElseThrow(()->new Exception("No order"));
        Item orderedItem1 = orderTestHelper.getLatestOrderItemId(0);
        Item orderedItem2 = orderTestHelper.getLatestOrderItemId(1);
        assertEquals(order.getStatus(), OrderStatus.WAITING_PAYMENT);
        assertEquals(100, orderedItem1.getStock());
        assertEquals(101, orderedItem2.getStock());
    }

    @Test
    @Transactional
    public void testCancelOrderAsAdmin() throws Exception {
        dataSeed.insertData();
        orderTestHelper.insertOrderForNewUser(orderTestHelper.insertNewUser());
        orderTestHelper.insertOrderForUser(orderTestHelper.getUser());
        long orderId = orderTestHelper.getLatestOrder().orElseThrow(()->new Exception("No order")).getId();
        mvc.perform(requestBuilder(HttpMethod.PATCH, orderTestHelper.getOneOrderRoute()+"/cancel", String.valueOf(orderId)))
                .andDo(print())
                .andExpect(status().isOk())
        ;
        Order order = orderTestHelper.getLatestOrder().orElseThrow(()->new Exception("No order"));
        Item orderedItem1 = orderTestHelper.getLatestOrderItemId(0);
        Item orderedItem2 = orderTestHelper.getLatestOrderItemId(1);
        assertEquals(order.getStatus(), OrderStatus.CANCELED);
        assertEquals(102, orderedItem1.getStock());
        assertEquals(108, orderedItem2.getStock());
    }
}
