package com.xtsshop.app.controller.orders;

import com.xtsshop.app.db.seed.DevelopmentDataSeed;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class PayTest extends OrdersTest {
    @Autowired
    private PayTestHelper payTestHelper;

    @Autowired
    private DevelopmentDataSeed dataSeed;

    @TestConfiguration
    public class TestConfig{
        @Bean
        PayTestHelper payTestUtil(){
            return new PayTestHelper();
        }
    }

    @Test
    public void test() throws Exception{
        dataSeed.insertData();
        orderTestHelper.insertDataWithNewUserOrder();
        long orderId = orderTestHelper.getLatestOrder().orElseThrow(()->new Exception("No order")).getId();
        setUserCredential("mario123", "123");
        mvc.perform(requestBuilder(HttpMethod.PATCH, payTestHelper.getRoute(orderTestHelper.getOneOrderRoute()), orderId)
                .content(mapper.writeValueAsString(payTestHelper.buildPaymentCreateForm()))
                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testPayAsAdmin() throws Exception{
        dataSeed.insertData();
        orderTestHelper.insertDataWithNewUserOrder();
        long orderId = orderTestHelper.getLatestOrder().orElseThrow(()->new Exception("No order")).getId();
        mvc.perform(requestBuilder(HttpMethod.PATCH, payTestHelper.getRoute(orderTestHelper.getOneOrderRoute()), orderId)
                        .content(mapper.writeValueAsString(payTestHelper.buildPaymentCreateForm()))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isForbidden());
    }

    @Test
    public void testPayAsOtherUser() throws Exception{
        dataSeed.insertData();
        orderTestHelper.insertDataWithNewUserOrder();
        long orderId = orderTestHelper.getLatestOrder().orElseThrow(()->new Exception("No order")).getId();
        setUserCredential(getUserUsername(), "123");
        mvc.perform(requestBuilder(HttpMethod.PATCH, payTestHelper.getRoute(orderTestHelper.getOneOrderRoute()), orderId)
                        .content(mapper.writeValueAsString(payTestHelper.buildPaymentCreateForm()))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isForbidden());
    }
}
