package com.xtsshop.app.features.users;

import com.xtsshop.app.form.users.CreateUserForm;
import com.xtsshop.app.db.entities.AppUser;
import com.xtsshop.app.TestCase;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class CreateTest extends TestCase {

    @Autowired
    private UserTestHelper userTestHelper;

    @Test
    public void test() throws Exception{
        CreateUserForm form = new CreateUserForm("chris321", "123456789", "ken321@gmail.com", "23224567");
        mvc.perform(post(userTestHelper.getRoute())
                .content(mapper.writeValueAsString(form))
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isCreated());
        AppUser user = userTestHelper.getUserJpaRepository().findUserByUsername("chris321");
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        assertNotNull(user);
        assertTrue(encoder.matches("123456789", user.getPassword()));
        assertEquals("ken321@gmail.com", user.getEmail());
    }

    @Test
    public void testInvalidUsername() throws Exception{
        CreateUserForm form = new CreateUserForm("chris32++1;;;", "123456789", "ken321@gmail.com", "23224567");
        MvcResult result = mvc.perform(post(userTestHelper.getRoute())
                .content(mapper.writeValueAsString(form))
                .contentType(MediaType.APPLICATION_JSON)
            )
                .andExpect(status().isUnprocessableEntity())
                .andReturn();
        JSONObject res = new JSONObject(result.getResponse().getContentAsString());
        String error = res.getString("username");
        assertEquals("must match \"^([0-9a-zA-Z])([0-9a-zA-Z]*)([0-9a-zA-Z])$\"", error);
    }

    @Test
    public void testInvalidPassword() throws Exception{
        CreateUserForm form = new CreateUserForm("chris3222", "12345", "ken321@gmail.com", "23224567");
        MvcResult result = mvc.perform(post(userTestHelper.getRoute())
                        .content(mapper.writeValueAsString(form))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isUnprocessableEntity())
                .andReturn();
        JSONObject res = new JSONObject(result.getResponse().getContentAsString());
        String error = res.getString("password");
        assertEquals("length must be between 8 and 2147483647", error);
    }

    @Test
    public void testInvalidEmail() throws Exception {
        CreateUserForm form = new CreateUserForm("chris3222", "12345777777", "ken321gmail.com", "23224567");
        MvcResult result = mvc.perform(post(userTestHelper.getRoute())
                        .content(mapper.writeValueAsString(form))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isUnprocessableEntity())
                .andReturn();
        JSONObject res = new JSONObject(result.getResponse().getContentAsString());
        String error = res.getString("email");
        assertEquals("must be a well-formed email address", error);
    }
    @Test
    public void testInvalidPhone() throws Exception {
        CreateUserForm form = new CreateUserForm("chris3222", "12345777777", "ken321@gmail.com", "232245=-=");
        MvcResult result = mvc.perform(post(userTestHelper.getRoute())
                        .content(mapper.writeValueAsString(form))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isUnprocessableEntity())
                .andReturn();
        JSONObject res = new JSONObject(result.getResponse().getContentAsString());
        String error = res.getString("phone");
        assertEquals("must match \"^[0-9a-zA-Z+]+$\"", error);
    }
}
