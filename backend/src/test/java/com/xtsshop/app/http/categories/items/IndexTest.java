package com.xtsshop.app.http.categories.items;

import com.xtsshop.app.db.repositories.CategoryRepository;
import com.xtsshop.app.http.MediaType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class IndexTest {
    @Autowired
    private Util util;
    @TestConfiguration
    public static class TestConfig{
        @Bean
        public Util util(MockMvc mockMvc, CategoryRepository categoryRepository){
            return new Util(mockMvc, categoryRepository);
        }
    }
    @Test
    void testCaseNormal() throws Exception{
        util.getMockMvc()
                .perform(get(util.getIndexRoute()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_HALJSON))
                .andExpect(jsonPath("$._embedded.itemModelList", hasSize(2)))
                .andExpect(jsonPath("$._embedded.itemModelList.[0].id", is(7)))
                .andExpect(jsonPath("$._embedded.itemModelList.[0].name", is("apple")))
                .andExpect(jsonPath("$._embedded.itemModelList.[0].price", is(12.2)))
                .andExpect(jsonPath("$._embedded.itemModelList.[0].manufacturer", is("manufacturer 1")))
                .andExpect(jsonPath("$._embedded.itemModelList.[1].id", is(8)))
                .andExpect(jsonPath("$._embedded.itemModelList.[1].name", is("orange")))
                .andExpect(jsonPath("$._embedded.itemModelList.[1].price", is(23.2)))
                .andExpect(jsonPath("$._embedded.itemModelList.[1].manufacturer", is("manufacturer 2")));

    }
}
