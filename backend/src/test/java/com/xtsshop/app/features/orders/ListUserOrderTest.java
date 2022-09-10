package com.xtsshop.app.features.orders;

import com.xtsshop.app.TestCase;
import com.xtsshop.app.db.entities.OrderStatus;
import com.xtsshop.app.features.orders.data.FakeNewUserDataSet;
import com.xtsshop.app.features.orders.data.FakeOrderDataSetBuilder;
import com.xtsshop.app.features.orders.data.FakeOrderedItemDataSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.ResultActions;

import org.junit.jupiter.api.Test;

import javax.transaction.Transactional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Import({
        ListUserOrderTestHelper.class,
        FakeNewUserDataSet.class,
        FakeOrderDataSetBuilder.class,
        FakeOrderedItemDataSet.class,
})
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class ListUserOrderTest extends TestCase {
    @Autowired
    private ListUserOrderTestHelper helper;

    private String route = "/api/users/{username}/orders";

    @Test
    @Transactional
    public void test() throws Exception {
        helper.insertData();


        ResultActions response = mvc.perform(
            requestBuilder(HttpMethod.GET, route, "marry123")
               .contentType(MediaType.APPLICATION_JSON)
        )
               .andExpect(status().isOk())
               .andDo(print());

        response.andExpect(jsonPath("$._embedded.orderRepresentationModelList.[0].items.[0].name", is("apple")))
               .andExpect(jsonPath("$._embedded.orderRepresentationModelList.[0].items.[0].price", is(12.2)))
               .andExpect(jsonPath("$._embedded.orderRepresentationModelList.[0].items.[0].quantity", is(2)))
               .andExpect(jsonPath("$._embedded.orderRepresentationModelList.[0].items.[1].name", is("orange")))
               .andExpect(jsonPath("$._embedded.orderRepresentationModelList.[0].items.[1].price", is(23.2)))
               .andExpect(jsonPath("$._embedded.orderRepresentationModelList.[0].items.[1].quantity", is(7)))
               .andExpect(jsonPath("$._embedded.orderRepresentationModelList.[0].orderStatus", is(OrderStatus.WAITING_PAYMENT.name())))
               .andExpect(jsonPath("$._embedded.orderRepresentationModelList", hasSize(1)));

    }

    @Test
    public void testAccessAsAdmin() throws Exception {
        helper.insertData();
        mvc.perform(
                        requestBuilder(HttpMethod.GET, route, "marry123")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$._embedded.orderRepresentationModelList.[0].items.[0].name", is("apple")))
                .andExpect(jsonPath("$._embedded.orderRepresentationModelList.[0].items.[0].price", is(12.2)))
                .andExpect(jsonPath("$._embedded.orderRepresentationModelList.[0].items.[0].quantity", is(2)))
                .andExpect(jsonPath("$._embedded.orderRepresentationModelList.[0].items.[1].name", is("orange")))
                .andExpect(jsonPath("$._embedded.orderRepresentationModelList.[0].items.[1].price", is(23.2)))
                .andExpect(jsonPath("$._embedded.orderRepresentationModelList.[0].items.[1].quantity", is(7)))
                .andExpect(jsonPath("$._embedded.orderRepresentationModelList.[0].orderStatus", is(OrderStatus.WAITING_PAYMENT.name())))
                .andExpect(jsonPath("$._embedded.orderRepresentationModelList", hasSize(1)));

    }

    @Test
    public void testAccessAsOtherUser() throws Exception {
        helper.insertData();
        setUserCredential("mario123", "123");
        mvc.perform(
                        requestBuilder(HttpMethod.GET, route, "marry123")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isForbidden());
    }
}
