package com.xtsshop.app.domain.service.items;

import com.xtsshop.app.advice.exception.RecordNotFoundException;
import com.xtsshop.app.db.entities.Item;
import com.xtsshop.app.db.repositories.ItemRepository;
import com.xtsshop.app.domain.request.items.ListItemsRequest;
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

    public List<Item> listItems(ListItemsRequest request){
        List<Long> itemIds = request.getItemIds();
        if(itemIds.size() <= 0){
            return listAllItems();
        }
        return listItemsByItemId(itemIds);
    }

    private List<Item> listItemsByItemId(List<Long> ids){
        return repository.findAllById(ids);
    }

    private List<Item> listAllItems(){
        return repository.findAll();
    }

    public Item get(Long id) {
        return repository
                .findById(id)
                .orElseThrow(()->new RecordNotFoundException("Item of id"+id+" not found."));
    }

}
