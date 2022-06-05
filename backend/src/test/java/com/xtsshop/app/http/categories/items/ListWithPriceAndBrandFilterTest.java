package com.xtsshop.app.http.categories.items;

import com.xtsshop.app.db.repositories.BrandRepository;
import com.xtsshop.app.db.repositories.CategoryRepository;
import com.xtsshop.app.http.MediaType;
import com.xtsshop.app.http.TestCase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class ListWithPriceAndBrandFilterTest extends TestCase {
    @Autowired
    private ListWithPriceAndBrandFilterUtil util;
    @TestConfiguration
    public static class TestConfig{
        @Bean
        public ListWithPriceAndBrandFilterUtil util(MockMvc mockMvc, CategoryRepository categoryRepository, BrandRepository brandRepository){
            return new ListWithPriceAndBrandFilterUtil(mockMvc, categoryRepository, brandRepository);
        }
    }

    @Test
    public void testCaseWithBrandAndPriceFilter() throws Exception{
        util.getMockMvc()
                .perform(get(util.getRoute())
                        .queryParam("categoryIds[]", util.getCategoryId()+"")
                        .queryParam("brandIds[]", util.getBrandId()+"")
                        .queryParam("maxPrice", util.getMaxPrice()+"")
                        .queryParam("minPrice", util.getMinPrice()+"")
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_HALJSON))
                .andExpect(jsonPath("$", anEmptyMap()));
    }

    @Test
    public void testCaseWithBrandFilter() throws Exception{
        util.getMockMvc()
                .perform(get(util.getRoute())
                        .queryParam("categoryIds[]", util.getCategoryId()+"")
                        .queryParam("brandIds[]", util.getBrandId()+"")
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_HALJSON))
                .andExpect(jsonPath("$._embedded.itemModelList", hasSize(1)))
                .andExpect(jsonPath("$._embedded.itemModelList.[0].name", is("apple")))
                .andExpect(jsonPath("$._embedded.itemModelList.[0].price.value", is(12.2)))
                .andExpect(jsonPath("$._embedded.itemModelList.[0].manufacturer", is("manufacturer 1")));

    }

    @Test
    public void testCaseWithPriceFilter() throws Exception{
        util.getMockMvc()
                .perform(get(util.getRoute())
                        .queryParam("categoryIds[]", util.getCategoryId()+"")
                        .queryParam("maxPrice", 23.2f+"")
                        .queryParam("minPrice", 23.1f+"")
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_HALJSON))
                .andExpect(jsonPath("$._embedded.itemModelList", hasSize(1)))
                .andExpect(jsonPath("$._embedded.itemModelList.[0].name", is("orange")))
                .andExpect(jsonPath("$._embedded.itemModelList.[0].price.value", is(23.2)))
                .andExpect(jsonPath("$._embedded.itemModelList.[0].manufacturer", is("manufacturer 2")));

    }
}
