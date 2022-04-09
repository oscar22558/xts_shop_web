package com.xtsshop.app.service.categories;

import com.xtsshop.app.advice.exception.RecordNotFoundException;
import com.xtsshop.app.db.entities.Category;
import com.xtsshop.app.db.entities.Item;
import com.xtsshop.app.db.repositories.CategoryRepository;
import com.xtsshop.app.request.CategoryRequest;
import com.xtsshop.app.service.items.ItemsService;
import com.xtsshop.app.util.CheckedFunction;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoriesGetService{
    private CategoryRepository repository;

    public CategoriesGetService(CategoryRepository repository) {
        this.repository = repository;
    }

    public Category get(Long id) throws RecordNotFoundException{
        return repository.findById(id).orElseThrow(()->new RecordNotFoundException("Category with id " + id + " not found."));
    }

}
