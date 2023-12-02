package com.xtsshop.app.features.users;

import com.xtsshop.app.form.users.UpdatePasswordForm;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class UpdatePasswordTest extends TestCase {

    @Autowired
    private UserTestHelper userTestHelper;

    @Test
    public void testWrongPassword() throws Exception{
        userTestHelper.insertData();
        UpdatePasswordForm form = new UpdatePasswordForm("333", "abc321uii");
        mvc.perform(requestBuilder(HttpMethod.PATCH, userTestHelper.getUpdatePasswordRoute())
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
        UpdatePasswordForm form = new UpdatePasswordForm("123", "abc321uii");
        mvc.perform(requestBuilder(HttpMethod.PATCH, userTestHelper.getUpdatePasswordRoute())
                .content(mapper.writeValueAsString(form))
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
        AppUser user = userTestHelper.getUserJpaRepository().findUserByUsername(getAdminUserUsername());
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        assertNotNull(user);
        assertTrue(encoder.matches("abc321uii", user.getPassword()));
        assertEquals("ken123@xts-shop.com", user.getEmail());
        assertEquals("23245566", user.getPhone());
    }


}
