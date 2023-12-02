package com.xtsshop.app.features.orders.models;

import com.xtsshop.app.AbstractRepresentationModel;
import com.xtsshop.app.db.entities.Order;
import com.xtsshop.app.features.users.payment.invoice.models.Invoice;

import java.sql.Date;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class OrderRepresentationModel implements AbstractRepresentationModel {
    private Order entity;

    public OrderRepresentationModel(Order entity){
        this.entity = entity;
    }

    public long getId(){
        return entity.getId();
    }

    public ShippingAddressRepresentationModel getShippingAddress(){
        return new ShippingAddressRepresentationModel(entity.getShippingAddress());
    }

    public String getOrderStatus(){
        return entity.getStatus().name();
    }

    public Collection<OrderedItemRepresentationModel> getItems(){
        List<OrderedItemRepresentationModel> models = entity.getOrderedItems()
                .stream()
                .map(OrderedItemRepresentationModel::new)
                .collect(Collectors.toList());
        return models;
    }

    public Date getOrderPlaced(){
        return entity.getCreatedAt();
    }

    public Invoice getInvoice(){
        float itemsTotal = entity.getInvoice().getItemsTotal();
        float shippingFee = entity.getInvoice().getShippingFee();
        float total = entity.getInvoice().getTotal();
        return new Invoice(itemsTotal, shippingFee, total);
    }

    public String getRecipientFirstName(){
        return entity.getRecipientFirstName();
    }

    public String getRecipientLastName(){
        return entity.getRecipientLastName();
    }

    public String getRecipientEmail(){
        return entity.getRecipientEmail();
    }

    public String getRecipientPhone(){
       return entity.getRecipientPhone();
    }
}
