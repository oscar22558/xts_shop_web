package com.xtsshop.app.controller.users;

import com.xtsshop.app.form.users.UpdateEmailForm;
import com.xtsshop.app.db.entities.AppUser;
import com.xtsshop.app.TestCase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
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
    private UserTestHelper userTestHelper;

    @Test
    public void testWrongPassword() throws Exception{
        userTestHelper.insertData();
        UpdateEmailForm form = new UpdateEmailForm("3333", "ken5354@gmail.com");
        mvc.perform(requestBuilder(HttpMethod.PATCH, userTestHelper.getUpdateEmailRoute())
                .content(mapper.writeValueAsString(form))
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isForbidden());
        AppUser user = userTestHelper.getUserJpaRepository().findUserByUsername(getAdminUserUsername());
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        assertNotNull(user);
        assertTrue(encoder.matches("123", user.getPassword()));
        assertEquals("ken123@xts-shop.com", user.getEmail());
        assertEquals("23245566", user.getPhone());
    }
    @Test
    public void test() throws Exception{
        userTestHelper.insertData();
        UpdateEmailForm form = new UpdateEmailForm("123", "ken5354@gmail.com");
        mvc.perform(requestBuilder(HttpMethod.PATCH, userTestHelper.getUpdateEmailRoute())
                .content(mapper.writeValueAsString(form))
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
        AppUser user = userTestHelper.getUserJpaRepository().findUserByUsername(getAdminUserUsername());
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        assertNotNull(user);
        assertTrue(encoder.matches("123", user.getPassword()));
        assertEquals("ken5354@gmail.com", user.getEmail());
        assertEquals("23245566", user.getPhone());
    }


}
