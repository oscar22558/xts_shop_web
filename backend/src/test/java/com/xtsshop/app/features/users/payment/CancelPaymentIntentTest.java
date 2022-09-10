package com.xtsshop.app.features.users.payment;


import com.xtsshop.app.DependencyTestConfig;
import com.xtsshop.app.LoadDatabaseTestConfig;
import com.xtsshop.app.TestCase;
import com.xtsshop.app.advices.exception.RecordNotFoundException;
import com.xtsshop.app.features.users.payment.data.FakeOrderData;
import com.xtsshop.app.features.users.payment.models.CancelPaymentIntentRequest;
import com.xtsshop.app.features.users.payment.models.FakePaymentDetail;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Import({
        LoadDatabaseTestConfig.class,
        DependencyTestConfig.class,
        CancelPaymentIntentTestHelper.class,
        FakeOrderData.class
})
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@ActiveProfiles("dev")
public class CancelPaymentIntentTest extends TestCase {

    @Autowired
    private CancelPaymentIntentTestHelper helper;

    @Test
    public void testWhenCancelPaymentIntentSentThenOrderIsDeleted() throws Exception {
        helper.insertData();
        sentCancelPaymentIntentRequest();
        assertOrderIsDelete();
    }

    private CancelPaymentIntentRequest buildCancelRequest(){
        CancelPaymentIntentRequest request = new CancelPaymentIntentRequest();
        request.setPaymentIntentId(FakePaymentDetail.PAYMENT_INTENT_ID);
        return request;
    }

    private void sentCancelPaymentIntentRequest() throws Exception {
        CancelPaymentIntentRequest request = buildCancelRequest();
        mvc.perform(
                requestBuilder(HttpMethod.DELETE, "/api/payment-intent")
                        .content(mapper.writeValueAsBytes(request))
                        .contentType(MediaType.APPLICATION_JSON)
        );
    }

    private void assertOrderIsDelete(){
        assertThrowsExactly(RecordNotFoundException.class, helper::getOrder);
    }
}
