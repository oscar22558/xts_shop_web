package com.xtsshop.app.repository.Categories.Helpers;

import com.xtsshop.app.db.entities.Category;
import com.xtsshop.app.db.repositories.CategoryRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class RecursiveCategorySearcher {
    private List<Long> topLevelCategoryIds;
    private CategoryRepository categoryRepository;

    public RecursiveCategorySearcher(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public void setTopLevelCategoryIds(List<Long> topLevelCategoryIds) {
        this.topLevelCategoryIds = topLevelCategoryIds;
    }

    public List<Long> search(){
        List<Category> categories = categoryRepository.findAllById(topLevelCategoryIds);
        return categories.stream()
                .flatMap((category)-> findCategoryIdsRecursively(category).stream())
                .collect(Collectors.toList());
    }
    public List<Long> findCategoryIdsRecursively(Category category){
        List<Long> ids = new ArrayList<>(List.of(category.getId()));
        List<Long> subCategoryIds = category.getSubCategories()
                .stream()
                .flatMap((subCategory)->findCategoryIdsRecursively(subCategory).stream())
                .collect(Collectors.toList());
        ids.addAll(subCategoryIds);
        return ids;
    }
}