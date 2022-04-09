package com.xtsshop.app.http;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xtsshop.app.db.entities.AppUser;
import com.xtsshop.app.db.repositories.UserRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TestCase {
    @Autowired
    protected MockMvc mvc;
    @Autowired
    protected ObjectMapper mapper;
    protected String authRoute = "/api/auth";
    protected AuthForm credential;

    public TestCase(){
        credential = new AuthForm("ken123", "123");
    }
    protected String getAuthRoute(){
        return authRoute;
    }

    protected String getAdminUserUsername(){ return "ken123"; }
    protected String getUserUsername(){ return "marry123"; }
    protected String getPassword(){return "123"; }
    protected AuthForm getCredential(){
        return this.credential;
    }
    protected void setUserCredential(String username, String password){
        credential = new AuthForm(username, password);
    }
    protected String getToken() throws Exception {
        AuthForm authForm = getCredential();

        MvcResult result = mvc.perform(
                    post(getAuthRoute()).content(mapper.writeValueAsString(authForm)).contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andReturn();
        JSONObject res = new JSONObject(result.getResponse().getContentAsString());
        return res.getString("token");
    }
    protected MockHttpServletRequestBuilder requestBuilder(HttpMethod method, String urlTemplate, Object... uriVars) throws Exception {
        return MockMvcRequestBuilders.request(method, urlTemplate, uriVars).header("Authorization", getToken());
    }
    protected MockHttpServletRequestBuilder addToken(MockHttpServletRequestBuilder builder) throws Exception {
        return builder.header("Authorization", getToken());
    }
}
