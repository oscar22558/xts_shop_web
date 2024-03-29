package com.xtsshop.app.features.orders;

import com.xtsshop.app.db.seed.DevDataSeed;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
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
public class IndexTest extends OrdersTest {
    @Autowired
    private DevDataSeed dataSeed;

    @Test
    void test() throws Exception {
        dataSeed.insertData();
        orderTestHelper.insertDataWithNewUserOrder();
        mvc.perform(requestBuilder(HttpMethod.GET, orderTestHelper.getRoute()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._embedded.orderModelList", hasSize(2)))
                .andExpect(jsonPath("$._embedded.orderModelList.[0].user.username", is("marry123")))
                .andExpect(jsonPath("$._embedded.orderModelList.[1].user.username", is("mario123")))
        ;
    }
    @Test
    void testAccessAsNormalUser() throws Exception {
        dataSeed.insertData();
        orderTestHelper.insertDataWithNewUserOrder();
        setUserCredential("marry123", "123");
        mvc.perform(requestBuilder(HttpMethod.GET, orderTestHelper.getRoute()))
                .andDo(print())
                .andExpect(status().isForbidden());

    }
}