package com.xtsshop.app.features.users.addresses;

import com.xtsshop.app.TestCase;
import com.xtsshop.app.features.users.addresses.models.AddressCreateForm;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.ResultActions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class AddUserAddressTest extends TestCase {
    @Autowired
    private AddUserAddressTestHelper helper;
    private ResultActions response;

    @Test
    public void testWhenAddAddressRequestSentThenResponseIsOk() throws Exception {
        helper.insertData();
        setUserCredential(getUserUsername(), "123");
        sendAddAddressRequest();
        assertResponseIsCreated();
    }

    @Test
    public void testWhenAddAddressRequestSentThenAddressIsAdded() throws Exception {
        helper.insertData();
        setUserCredential(getUserUsername(), "123");
        sendAddAddressRequest();
        assertAddressIsAdded();
    }

    private void assertAddressIsAdded(){
        int addressCount = helper.countUserAddress("marry123");
        assertEquals(2, addressCount);
    }

    private void assertResponseIsCreated() throws Exception {
        response.andExpect(status().isCreated());
    }

    private void sendAddAddressRequest() throws Exception {
        AddressCreateForm form = buildAddAddressRequest();
        response = mvc.perform(
                requestBuilder(HttpMethod.POST, "/api/users/address")
                .content(mapper.writeValueAsBytes(form))
                .contentType(MediaType.APPLICATION_JSON)
        );
    }

    private AddressCreateForm buildAddAddressRequest(){
        AddressCreateForm form = new AddressCreateForm();
        form.setRow1("HKU MB166");
        form.setDistrict("Central and Western");
        form.setArea("Hong Kong");
        form.setCity("Hong Kong");
        form.setCountry("China");
        return form;
    }
}
