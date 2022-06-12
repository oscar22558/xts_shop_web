package com.xtsshop.app.controller.users.carts;

import com.xtsshop.app.db.repositories.ItemRepository;
import com.xtsshop.app.db.repositories.UserRepository;
import com.xtsshop.app.TestCase;
import com.xtsshop.app.form.users.carts.CartForm;
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

import static org.springframework.http.RequestEntity.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class AddItemsTest extends TestCase {
    @Autowired
    private Util util;
    @TestConfiguration
    public static class TestConfig{
        @Bean
        public Util util(ItemRepository itemRepository, UserRepository userRepository){
            return new Util(itemRepository, userRepository);
        }
    }
    @Test
    public void test() throws Exception{
        List<Long> itemIds = util.getItems().stream().map(item->item.getId()).collect(Collectors.toList());
        CartForm form = new CartForm(itemIds);
        setUserCredential(getUserUsername(), getPassword());
        mvc.perform(requestBuilder(HttpMethod.POST, util.getRoute()).contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(form)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$._embedded.itemModelList.[0].name").value("orange"))
                .andExpect(jsonPath("$._embedded.itemModelList.[0].price.value").value(23.2))
                .andExpect(jsonPath("$._embedded.itemModelList.[1].name").value("USB data cable"))
                .andExpect(jsonPath("$._embedded.itemModelList.[1].price.value").value(44.2));
    }
}
