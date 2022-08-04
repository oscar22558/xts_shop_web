package com.xtsshop.app.controller.users.carts;

import com.xtsshop.app.db.repositories.ItemJpaRepository;
import com.xtsshop.app.db.repositories.UserJpaRepository;
import com.xtsshop.app.TestCase;
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
        public DataSet dataSet(UserJpaRepository userJpaRepository, ItemJpaRepository itemJpaRepository){
            return new DataSet(userJpaRepository, itemJpaRepository);
        }
        @Bean
        public Util util(ItemJpaRepository itemJpaRepository, UserJpaRepository userJpaRepository){
            return new Util(itemJpaRepository, userJpaRepository);
        }
    }
    @Test
    public void test() throws Exception {
        dataSet.insert();
        setUserCredential(getUserUsername(), getPassword());
        mvc.perform(requestBuilder(HttpMethod.GET, util.getRoute()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._embedded.itemRepresentationModelList", hasSize(2)))
                .andExpect(jsonPath("$._embedded.itemRepresentationModelList.[0].name").value("orange"))
                .andExpect(jsonPath("$._embedded.itemRepresentationModelList.[1].name").value("USB data cable"));
    }
}
