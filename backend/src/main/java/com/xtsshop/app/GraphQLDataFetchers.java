package com.xtsshop.app;

import com.xtsshop.app.viewmodel.CategoryModel;
import com.xtsshop.app.db.repositories.CategoryRepository;
import graphql.schema.DataFetcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class GraphQLDataFetchers {

    private static final Logger log = LoggerFactory.getLogger(GraphQLDataFetchers.class);
    @Autowired
    CategoryRepository categoryRepository;

    public DataFetcher getCategoryFetcher() {
        return dataFetchingEnvironment -> {
            String bookId = dataFetchingEnvironment.getArgument("id");
            return categoryRepository.findById(Long.parseLong(bookId)).stream().map(CategoryModel::from);
        };
    }

    public DataFetcher getAllCategoriesFetcher() {
        return dataFetchingEnvironment ->
                categoryRepository
                        .findAll()
                        .stream()
                        .filter(category -> category.getParent() == null)
                        .map(CategoryModel::from)
                        .collect(Collectors.toList());
    }
    public  DataFetcher getSubCategoryFetcher(){
        return dataFetchingEnvironment -> {
            CategoryModel parentCategory = dataFetchingEnvironment.getSource();
            return parentCategory.getSubCategories();
        };
    }
}
