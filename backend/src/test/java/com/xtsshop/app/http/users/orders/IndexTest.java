package com.xtsshop.app.http.users.orders;

import com.xtsshop.app.db.repositories.ItemRepository;
import com.xtsshop.app.db.repositories.OrderRepository;
import com.xtsshop.app.db.repositories.RoleRepository;
import com.xtsshop.app.db.repositories.UserRepository;
import com.xtsshop.app.form.orders.OrderListForm;
import com.xtsshop.app.http.TestCase;
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
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class IndexTest extends TestCase {
    @Autowired
    private Util util;

    @TestConfiguration
    public static class TestConfig{
        @Bean
        Util util(
                ItemRepository itemRepository,
                UserRepository userRepository,
                OrderRepository orderRepository,
                RoleRepository roleRepository
        ){
            return new Util(itemRepository, userRepository, orderRepository, roleRepository);
        }
    }

    @Test
    @Transactional
    public void test() throws Exception {
        util.insertOrderForUser();
        util.insertOrderForUser();
        util.insertNewUser();
        OrderListForm form = new OrderListForm();
        form.setUsername("marry123");
       mvc.perform(
               requestBuilder(HttpMethod.GET, util.getRoute())
                       .content(mapper.writeValueAsBytes(form))
                       .contentType(MediaType.APPLICATION_JSON)
       )
               .andExpect(status().isOk())
               .andDo(print())
               .andExpect(jsonPath("$._embedded.orderModelList.[0].items.[0].item.name", is("apple")))
               .andExpect(jsonPath("$._embedded.orderModelList.[0].items.[0].item.price.value", is(12.2)))
               .andExpect(jsonPath("$._embedded.orderModelList.[0].items.[0].quantity", is(2)))
               .andExpect(jsonPath("$._embedded.orderModelList.[0].items.[1].item.name", is("orange")))
               .andExpect(jsonPath("$._embedded.orderModelList.[0].items.[1].item.price.value", is(23.2)))
               .andExpect(jsonPath("$._embedded.orderModelList.[0].items.[1].quantity", is(7)))
               .andExpect(jsonPath("$._embedded.orderModelList.[0].orderStatus", is("WAITING_PAYMENT")))
               .andExpect(jsonPath("$._embedded.orderModelList.[0].user.username", is("marry123")))
               .andExpect(jsonPath("$._embedded.orderModelList", hasSize(2))
               );

    }
}
