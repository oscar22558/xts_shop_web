package com.xtsshop.app.domain.service.items;

import com.xtsshop.app.advice.exception.RecordNotFoundException;
import com.xtsshop.app.db.entities.Item;
import com.xtsshop.app.db.repositories.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QueryItemsService {

    private ItemRepository repository;

    public QueryItemsService(
        ItemRepository repository
    ){
        this.repository = repository;
    }

    public List<Item> all(){
        return repository.findAll();
    }

    public Item get(Long id) {
        return repository.findById(id).orElseThrow(()->new RecordNotFoundException("Item of id"+id+" not found."));
    }

}
