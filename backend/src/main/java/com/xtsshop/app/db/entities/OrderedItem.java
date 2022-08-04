package com.xtsshop.app.db.entities;

import com.xtsshop.app.features.users.payment.exception.UpdateOrderQuantityException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.sql.Date;

@Setter
@Getter
@NoArgsConstructor
@Entity(name = "ordered_items")
public class OrderedItem extends AppEntity {
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Order order;
    @ManyToOne
    @JoinColumn(name = "item_id", referencedColumnName = "id")
    private Item item;
    @ManyToOne
    @JoinColumn(name = "price_history_id", referencedColumnName = "id")
    private PriceHistory orderPrice;

    public OrderedItem(Date createAt, Date updated, Item item, int quantity){
        super(createAt, updated);
        this.quantity = quantity;
        this.item = item;
    }

    public float getOrderPriceValue(){
        if(orderPrice == null){
            return item.getLatestPrice();
        }
        return orderPrice.getValue();
    }

    public void updateQuantity(int newQuantity){
        if(item == null){
            throw new UpdateOrderQuantityException("Item cannot be null.");
        }
        int diffInQuantity = newQuantity - quantity;
        boolean isNotUpdatable = diffInQuantity > item.getStock();
        if(isNotUpdatable){
            throw new UpdateOrderQuantityException("Item has insufficient stock.");
        }
        int newStock = item.getStock() - diffInQuantity;
        quantity = newQuantity;
        item.setStock(newStock);
    }
}
