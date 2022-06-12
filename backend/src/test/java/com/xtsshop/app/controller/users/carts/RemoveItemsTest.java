package com.xtsshop.app.controller.users.carts;

import com.xtsshop.app.db.repositories.ItemRepository;
import com.xtsshop.app.db.repositories.UserRepository;
import com.xtsshop.app.TestCase;
import com.xtsshop.app.form.users.carts.CartForm;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class RemoveItemsTest extends TestCase {
    @Autowired
    private DataSet dataSet;

    @Autowired
    private Util util;
    @TestConfiguration
    public static class TestConfig{
        @Bean
        public DataSet dataSet(UserRepository userRepository, ItemRepository itemRepository){
            return new DataSet(userRepository, itemRepository);
        }
        @Bean
        public Util util(ItemRepository itemRepository, UserRepository userRepository){
            return new Util(itemRepository, userRepository);
        }
    }
    @BeforeEach
    public void beforeEach() {
       dataSet.insert();
    }
    @Test
    public void test() throws Exception{
        List<Long> ids = util.getItems().stream().map(item->item.getId()).collect(Collectors.toList());
        CartForm form = new CartForm(ids);
        setUserCredential(getUserUsername(), getPassword());
        mvc.perform(requestBuilder(HttpMethod.DELETE, util.getRoute())
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(form))
        ).andExpect(status().isOk());
        assertEquals(0, util.getItemsInCart(getUserUsername()).size());
    }
}