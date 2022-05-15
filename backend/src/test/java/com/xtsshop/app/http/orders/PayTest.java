package com.xtsshop.app.http.orders;

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

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class PayTest extends OrdersTest {
    @Autowired
    private PayTestUtil payTestUtil;
    @TestConfiguration
    public class TestConfig{
        @Bean
        PayTestUtil payTestUtil(){
            return new PayTestUtil();
        }
    }

    @Test
    public void test() throws Exception{
        util.insertDataWithNewUserOrder();
        long orderId = util.getLatestOrder().orElseThrow(()->new Exception("No order")).getId();
        setUserCredential("mario123", "123");
        mvc.perform(requestBuilder(HttpMethod.PATCH, payTestUtil.getRoute(util.getGetRequestRoute()), orderId)
                .content(mapper.writeValueAsString(payTestUtil.buildPaymentCreateForm()))
                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testPayAsAdmin() throws Exception{
        util.insertDataWithNewUserOrder();
        long orderId = util.getLatestOrder().orElseThrow(()->new Exception("No order")).getId();
        mvc.perform(requestBuilder(HttpMethod.PATCH, payTestUtil.getRoute(util.getGetRequestRoute()), orderId)
                        .content(mapper.writeValueAsString(payTestUtil.buildPaymentCreateForm()))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isForbidden());
    }

    @Test
    public void testPayAsOtherUser() throws Exception{
        util.insertDataWithNewUserOrder();
        long orderId = util.getLatestOrder().orElseThrow(()->new Exception("No order")).getId();
        setUserCredential(getUserUsername(), "123");
        mvc.perform(requestBuilder(HttpMethod.PATCH, payTestUtil.getRoute(util.getGetRequestRoute()), orderId)
                        .content(mapper.writeValueAsString(payTestUtil.buildPaymentCreateForm()))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isForbidden());
    }
}
