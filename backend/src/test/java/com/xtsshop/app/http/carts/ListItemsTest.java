package com.xtsshop.app.http.carts;

import com.xtsshop.app.db.repositories.ItemRepository;
import com.xtsshop.app.db.repositories.UserRepository;
import com.xtsshop.app.http.TestCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.test.annotation.DirtiesContext;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class ListItemsTest extends TestCase {
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
    @Test
    public void test() throws Exception {
        dataSet.insert();
        setUserCredential(getUserUsername(), getPassword());
        mvc.perform(requestBuilder(HttpMethod.GET, util.getRoute()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._embedded.itemModelList", hasSize(2)))
                .andExpect(jsonPath("$._embedded.itemModelList.[0].name").value("orange"))
                .andExpect(jsonPath("$._embedded.itemModelList.[1].name").value("USB data cable"));
    }
}
