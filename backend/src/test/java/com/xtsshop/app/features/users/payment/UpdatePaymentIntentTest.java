package com.xtsshop.app.features.users.payment;

import com.xtsshop.app.DependencyTestConfig;
import com.xtsshop.app.LoadDatabaseTestConfig;
import com.xtsshop.app.TestCase;
import com.xtsshop.app.features.users.payment.data.FakeOrderData;
import com.xtsshop.app.features.users.payment.models.CreatePaymentIntentForm;
import com.xtsshop.app.features.users.payment.models.ItemQuantity;
import com.xtsshop.app.features.users.payment.models.UpdatePaymentIntentForm;
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
@Import({FakeOrderData.class, UpdatePaymentIntentTestHelper.class, LoadDatabaseTestConfig.class, DependencyTestConfig.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@ActiveProfiles("dev")
public class UpdatePaymentIntentTest extends TestCase {
    private ResultActions response;

    @Autowired
    private UpdatePaymentIntentTestHelper helper;

    @Test
    public void testWhenUpdatePaymentIntentRequestSentThenResponseIsOk() throws Exception {
        helper.insertData();
        sendRequest();
        assertResponseIsOk();
    }

    @Test
    public void testWhenUpdatePaymentIntentRequestSentThenOrderIsUpdated() throws Exception {
        helper.insertData();
        sendRequest();
        assertOrderIsUpdated();
    }

    @Test
    public void testWhenUpdatePaymentIntentRequestSentThenItemStockIsUpdated() throws Exception {
        helper.insertData();
        sendRequest();
        assertStockIsUpdated();
    }

    @Test
    public void testWhenUpdatePaymentIntentRequestSentThenInvoiceIsUpdated() throws Exception {
        helper.insertData();
        sendRequest();
        assertOrderInvoiceUpdated();
    }

    private UpdatePaymentIntentForm buildPostForm(){
        long addressId = helper.getShippingAddressIdByUsername(getUserUsername());
        long itemToOrder = helper.getItemIdToOrder();

        ItemQuantity itemQuantity = new ItemQuantity();
        itemQuantity.setItemId(itemToOrder);
        itemQuantity.setQuantity(11);
        List<ItemQuantity> itemQuantities = new ArrayList<>();
        itemQuantities.add(itemQuantity);

        UpdatePaymentIntentForm form = new UpdatePaymentIntentForm();
        form.setItemQuantities(itemQuantities);
        form.setUserAddressId(addressId);
        form.setClientSecret("12312436t34t234");
        return form;
    }

    private void sendRequest() throws Exception {
        setUserCredential(getUserUsername(), "123");
        CreatePaymentIntentForm form = buildPostForm();
        response = mvc.perform(
                requestBuilder(HttpMethod.PUT, "/api/payment-intent")
                        .content(mapper.writeValueAsBytes(form))
                        .contentType(MediaType.APPLICATION_JSON)
        );
    }

    private void assertResponseIsOk() throws Exception {
        response.andExpect(status().isOk());
    }

    private void assertOrderIsUpdated(){
        assertEquals(11, helper.getLatestOrderItem(0).getQuantity());
    }

    private void assertStockIsUpdated(){
        assertEquals(97, helper.getItemStock(0));
    }

    private void assertOrderInvoiceUpdated(){
        assertEquals(134.2f, helper.getLatestOrderInvoice().getItemsTotal());
        assertEquals(20, helper.getLatestOrderInvoice().getShippingFee());
        assertEquals(154.2f, helper.getLatestOrderInvoice().getTotal());
    }
}
