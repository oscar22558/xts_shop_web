package com.xtsshop.app.controller.users.addresses;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.xtsshop.app.TestCase;
import com.xtsshop.app.controller.users.addresses.models.AddressUpdateForm;
import com.xtsshop.app.db.entities.Address;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class EditAddressTest extends TestCase {

    private ResultActions response;

    @Autowired
    private EditAddressTestHelper helper;

    @Test
    public void testWhenEditAddressRequestSentThenResponseOk() throws Exception {
        helper.insertData();
        sendUpdateAddressRequest();
        assertResponseOk();
    }

    @Test
    public void testWhenUpdateAddressRequestSentThenAddressIsUpdated() throws Exception {
        helper.insertData();
        sendUpdateAddressRequest();
        assertAddressIsUpdated();
    }


    private void sendUpdateAddressRequest() throws Exception{
        setUserCredential(getUserUsername(), "123");
        AddressUpdateForm form = buildUpdateAddressRequestForm();
        String route = "/api/users/address/{addressId}";
        long targetAddressId = helper.getIdOfAddressToUpdated();
        response = mvc.perform(
                requestBuilder(HttpMethod.PUT, route, targetAddressId)
                .content(mapper.writeValueAsString(form))
                .contentType(MediaType.APPLICATION_JSON)
        );
    }

    private AddressUpdateForm buildUpdateAddressRequestForm() {
        AddressUpdateForm form = new AddressUpdateForm();
        form.setRow1("HKU MB100");
        form.setDistrict("Central and Western");
        form.setArea("Hong Kong");
        form.setCity("Hong Kong");
        form.setCountry("China");
        return form;
    }

    private void assertResponseOk() throws Exception {
       response.andExpect(status().isOk());
    }

    private void assertAddressIsUpdated(){
        assertEquals("HKU MB100", helper.getUpdatedAddressRow1());
    }
}
