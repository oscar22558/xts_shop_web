package com.xtsshop.app.controller.users.addresses;

import com.xtsshop.app.TestCase;
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

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class DeleteAddressTest extends TestCase {
    private ResultActions response;

    @Autowired
    private DeleteAddressTestHelper helper;

    @Test
    public void testWhenDeleteAddressRequestSentThenResponseOk() throws Exception {
        helper.insertData();
        sendDeleteAddressRequest();
        assertResponseOk();
    }

    @Test
    public void testWhenDeleteAddressRequestSentThenAddressIsDeleted() throws Exception {
        helper.insertData();
        sendDeleteAddressRequest();
        assertAddressIsDeleted();
    }

    private void sendDeleteAddressRequest() throws Exception{
        setUserCredential(getUserUsername(), "123");
        String route = "/api/users/address/{addressId}";
        long targetAddressId = helper.getIdOfAddressToDelete();
        response = mvc.perform(
                requestBuilder(HttpMethod.DELETE, route, targetAddressId)
                        .contentType(MediaType.APPLICATION_JSON)
        );
    }

    private void assertResponseOk() throws Exception {
        response.andExpect(status().isOk());
    }

    private void assertAddressIsDeleted() throws Exception{
        assertEquals(0, helper.countAddressOfUser("marry123"));
    }
}
