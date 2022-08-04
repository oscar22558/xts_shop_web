package com.xtsshop.app.controller.users.payment;


import com.xtsshop.app.DependencyTestConfig;
import com.xtsshop.app.LoadDatabaseTestConfig;
import com.xtsshop.app.controller.users.payment.data.FakeOrderData;
import com.xtsshop.app.db.entities.OrderStatus;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Import({
        PaymentIntentSucceededServiceTestHelper.class,
        LoadDatabaseTestConfig.class,
        DependencyTestConfig.class,
        FakeOrderData.class
})
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@ActiveProfiles("dev")
public class PaymentIntentSucceededServiceTest{
    @Autowired
    private PaymentIntentSucceededService service;
    @Autowired
    private PaymentIntentSucceededServiceTestHelper helper;

    @Test
    public void testWhenServiceIsCalledThenOrderStatusIsChangedToPaid(){
        helper.insertData();
        service.handleEvent(helper.getOrderPaymentIntentId());
        assertOrderStatusIsPaid();
    }

    private void assertOrderStatusIsPaid(){
        assertEquals(OrderStatus.PAID, helper.getOrderStatus());
    }

}
