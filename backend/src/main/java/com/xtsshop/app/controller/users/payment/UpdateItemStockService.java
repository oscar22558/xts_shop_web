package com.xtsshop.app.controller.users.payment;

import com.xtsshop.app.controller.orders.exceptions.InsufficientItemStockException;
import com.xtsshop.app.controller.orders.exceptions.ItemOutOfStockException;
import com.xtsshop.app.db.entities.Item;
import org.springframework.stereotype.Service;

@Service
public class UpdateItemStockService {
    public void updateItemStock(Item item, int orderedQuantity){
        int itemStock = item.getStock();
        if(itemStock - orderedQuantity < 0){
            throw new InsufficientItemStockException("Item "+item.getName()+" does not have enough stock");
        }
        if(itemStock <= 0){
            throw ItemOutOfStockException.build();
        }
        item.setStock(itemStock - orderedQuantity);
    }
}
