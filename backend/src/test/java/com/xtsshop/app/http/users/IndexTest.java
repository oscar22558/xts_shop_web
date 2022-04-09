package com.xtsshop.app.http.users;

import com.xtsshop.app.db.entities.AppUser;
import com.xtsshop.app.db.repositories.UserRepository;
import com.xtsshop.app.http.TestCase;
import com.xtsshop.app.http.users.form.UpdatePasswordForm;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class IndexTest extends TestCase {

    @Autowired
    private Util util;
    @TestConfiguration
    public static class TestConfig{
        @Bean
        Util util(UserRepository userRepository){
            return new Util(userRepository);
        }
    }

    @Test
    public void test() throws Exception {
        mvc.perform(requestBuilder(HttpMethod.GET, util.getRoute()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._embedded.userViewModelList.[0].id").exists())
                .andExpect(jsonPath("$._embedded.userViewModelList.[0].username").value("ken123"))
                .andExpect(jsonPath("$._embedded.userViewModelList.[0].email").value("ken123@xts-shop.com"))
                .andExpect(jsonPath("$._embedded.userViewModelList.[0].phone").value("23245566"))
                .andExpect(jsonPath("$._embedded.userViewModelList.[1].id").exists())
                .andExpect(jsonPath("$._embedded.userViewModelList.[1].username").value("marry123"))
                .andExpect(jsonPath("$._embedded.userViewModelList.[1].email").value("marry123@xts-shop.com"))
                .andExpect(jsonPath("$._embedded.userViewModelList.[1].phone").value("28735601"));

    }


}
