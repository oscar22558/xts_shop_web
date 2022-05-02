package com.xtsshop.app.http.orders;

import com.xtsshop.app.db.repositories.ItemRepository;
import com.xtsshop.app.db.repositories.OrderRepository;
import com.xtsshop.app.db.repositories.RoleRepository;
import com.xtsshop.app.db.repositories.UserRepository;
import com.xtsshop.app.http.TestCase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;

import javax.transaction.Transactional;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class IndexTest extends TestCase {
    @Autowired
    private Util util;

    @TestConfiguration
    public static class TestConfig{
        @Bean
        Util util(OrderRepository orderRepository, UserRepository userRepository, RoleRepository roleRepository, ItemRepository itemRepository) {
            return new Util(orderRepository, userRepository, roleRepository, itemRepository);
        }
    }

    @Test
    void test() throws Exception {
        util.insertData();
        mvc.perform(requestBuilder(HttpMethod.GET, util.getRoute()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._embedded.orderModelList", hasSize(2)))
                .andExpect(jsonPath("$._embedded.orderModelList.[0].user.username", is("marry123")))
                .andExpect(jsonPath("$._embedded.orderModelList.[1].user.username", is("mario123")))
        ;
    }
    @Test
    void testAccessAsNormalUser() throws Exception {
        util.insertData();
        setUserCredential("marry123", "123");
        mvc.perform(requestBuilder(HttpMethod.GET, util.getRoute()))
                .andDo(print())
                .andExpect(status().isForbidden());

    }
}