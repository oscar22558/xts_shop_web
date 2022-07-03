package com.xtsshop.app.controller.users;

import com.xtsshop.app.db.repositories.UserRepository;
import com.xtsshop.app.TestCase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.test.annotation.DirtiesContext;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class OneTest extends TestCase {
    @Autowired
    private UserTestHelper userTestHelper;

    @Test
    public void test() throws Exception {
        userTestHelper.insertData();
        mvc.perform(requestBuilder(HttpMethod.GET, userTestHelper.getOneRoute(), "ken123"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.username").value("ken123"))
                .andExpect(jsonPath("$.email").value("ken123@xts-shop.com"))
                .andExpect(jsonPath("$.phone").value("23245566"));

    }


}
