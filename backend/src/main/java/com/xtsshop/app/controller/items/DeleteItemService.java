package com.xtsshop.app.controller.items;

import com.xtsshop.app.db.repositories.ItemJpaRepository;
import org.springframework.stereotype.Service;

@Service
public class DeleteItemService {

    private ItemJpaRepository repository;

    public DeleteItemService(ItemJpaRepository repository) {
        this.repository = repository;
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
