package com.xtsshop.app.http.users;

import com.xtsshop.app.db.entities.AppUser;
import com.xtsshop.app.db.repositories.UserRepository;
import com.xtsshop.app.http.TestCase;
import com.xtsshop.app.http.users.form.CreateUserForm;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class CreateTest extends TestCase {

    @Autowired
    private Util util;
    @TestConfiguration
    public static class TestConfig{
        @Bean
        Util util(UserRepository userRepository){
            return new Util(userRepository);
        }
    }

    @Test
    public void test() throws Exception{
        CreateUserForm form = new CreateUserForm("chris321", "123456789", "ken321@gmail.com", "23224567");
        mvc.perform(post(util.getRoute())
                .content(mapper.writeValueAsString(form))
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isCreated());
        AppUser user = util.getUserRepository().findUserByUsername("chris321");
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        assertNotNull(user);
        assertTrue(encoder.matches("123456789", user.getPassword()));
        assertEquals("ken321@gmail.com", user.getEmail());
    }

    @Test
    public void testInvalidUsername() throws Exception{
        CreateUserForm form = new CreateUserForm("chris32++1;;;", "123456789", "ken321@gmail.com", "23224567");
        MvcResult result = mvc.perform(post(util.getRoute())
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
        MvcResult result = mvc.perform(post(util.getRoute())
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
        MvcResult result = mvc.perform(post(util.getRoute())
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
        MvcResult result = mvc.perform(post(util.getRoute())
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
