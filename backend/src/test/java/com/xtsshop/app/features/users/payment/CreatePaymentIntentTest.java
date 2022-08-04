package com.xtsshop.app.features.users.payment;

import com.xtsshop.app.DependencyTestConfig;
import com.xtsshop.app.LoadDatabaseTestConfig;
import com.xtsshop.app.TestCase;
import com.xtsshop.app.features.users.payment.models.CreatePaymentIntentForm;
import com.xtsshop.app.features.users.payment.models.ItemQuantity;
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
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Import({CreatePaymentIntentTestHelper.class, LoadDatabaseTestConfig.class, DependencyTestConfig.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@ActiveProfiles("dev")
public class CreatePaymentIntentTest extends TestCase {
    private ResultActions response;

    @Autowired
    private CreatePaymentIntentTestHelper helper;

    @Test
    public void testWhenCreatePaymentIntentRequestSentThenResponseIsCreated() throws Exception {
        helper.insertData();
        sendRequest();
        assertResponseIsCreated();
    }

    @Test
    public void testWhenCreatePaymentIntentRequestSentThenOrderIsCreated() throws Exception {
        helper.insertData();
        sendRequest();
        assertOrderIsCreate();
    }

    private CreatePaymentIntentForm buildPostForm(){
        long addressId = helper.getShippingAddressIdByUsername(getUserUsername());
        long itemToOrder = helper.getItemIdToOrder();

        ItemQuantity itemQuantity = new ItemQuantity();
        itemQuantity.setItemId(itemToOrder);
        itemQuantity.setQuantity(10);
        List<ItemQuantity> itemQuantities = new ArrayList<>();
        itemQuantities.add(itemQuantity);

        CreatePaymentIntentForm form = new CreatePaymentIntentForm();
        form.setItemQuantities(itemQuantities);
        form.setUserAddressId(addressId);
        return form;
    }

    private void sendRequest() throws Exception {
        setUserCredential(getUserUsername(), "123");
        CreatePaymentIntentForm form = buildPostForm();
        response = mvc.perform(
                requestBuilder(HttpMethod.POST, "/api/payment-intent")
                        .content(mapper.writeValueAsBytes(form))
                        .contentType(MediaType.APPLICATION_JSON)
        );
    }

    private void assertResponseIsCreated() throws Exception {
        response.andExpect(status().isCreated());
    }

    private void assertOrderIsCreate(){
        assertEquals(helper.getItemIdToOrder(), helper.getLatestOrderItem(0).getItem().getId());
    }
}
