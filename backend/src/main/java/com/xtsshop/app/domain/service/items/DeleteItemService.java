package com.xtsshop.app.domain.service.items;

import com.xtsshop.app.db.repositories.ItemRepository;
import org.springframework.stereotype.Service;

@Service
public class DeleteItemService {

    private ItemRepository repository;

    public DeleteItemService(ItemRepository repository) {
        this.repository = repository;
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
