package com.xtsshop.app.http.users;

import com.xtsshop.app.db.entities.AppUser;
import com.xtsshop.app.db.repositories.UserRepository;
import com.xtsshop.app.http.TestCase;
import com.xtsshop.app.http.users.form.CreateUserForm;
import com.xtsshop.app.http.users.form.UpdateEmailForm;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class UpdateEmailTest extends TestCase {

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
    public void testWrongPassword() throws Exception{
        UpdateEmailForm form = new UpdateEmailForm("3333", "ken5354@gmail.com");
        mvc.perform(requestBuilder(HttpMethod.PATCH, util.getUpdateEmailRoute())
                .content(mapper.writeValueAsString(form))
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isForbidden());
        AppUser user = util.getUserRepository().findUserByUsername(getAdminUserUsername());
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        assertNotNull(user);
        assertTrue(encoder.matches("123", user.getPassword()));
        assertEquals("ken123@xts-shop.com", user.getEmail());
        assertEquals("23245566", user.getPhone());
    }
    @Test
    public void test() throws Exception{
        UpdateEmailForm form = new UpdateEmailForm("123", "ken5354@gmail.com");
        mvc.perform(requestBuilder(HttpMethod.PATCH, util.getUpdateEmailRoute())
                .content(mapper.writeValueAsString(form))
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
        AppUser user = util.getUserRepository().findUserByUsername(getAdminUserUsername());
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        assertNotNull(user);
        assertTrue(encoder.matches("123", user.getPassword()));
        assertEquals("ken5354@gmail.com", user.getEmail());
        assertEquals("23245566", user.getPhone());
    }


}
