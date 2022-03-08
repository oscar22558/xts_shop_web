package com.xtsshop.app.http.categories.items;

import com.xtsshop.app.db.repositories.CategoryRepository;
import lombok.Getter;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;

@Getter
@Component("tests.http.categories.items.Util")
public class Util {
    private CategoryRepository categoryRepository;
    private MockMvc mockMvc;
    private String route = "/api/categories";
    public Util(MockMvc mockMvc, CategoryRepository categoryRepository){
        this.mockMvc = mockMvc;
        this.categoryRepository = categoryRepository;
    }
    public long getCategoryId(){
        return categoryRepository.findAll().stream().findFirst().orElseThrow().getId();
    }

    public String getIndexRoute(){
        return route + "/" + getCategoryId() + "/items";
    }
}
