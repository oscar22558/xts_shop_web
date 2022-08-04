package com.xtsshop.app.controller.categories;

import com.xtsshop.app.advices.exception.RecordNotFoundException;
import com.xtsshop.app.db.entities.Category;
import com.xtsshop.app.db.repositories.CategoryJpaRepository;
import org.springframework.stereotype.Service;

@Service
public class GetCategoriesService {
    private CategoryJpaRepository repository;

    public GetCategoriesService(CategoryJpaRepository repository) {
        this.repository = repository;
    }

    public Category get(Long id) throws RecordNotFoundException{
        return repository.findById(id).orElseThrow(()->new RecordNotFoundException("Category with id " + id + " not found."));
    }

}
