package com.xtsshop.app.features.users.payment;

import com.xtsshop.app.db.entities.Invoice;
import com.xtsshop.app.features.users.payment.models.ItemQuantity;

import java.util.List;

public interface OrderTotalCalculator {
    void setItemQuantities(List<ItemQuantity> itemQuantities);
    float getItemsTotal(List<ItemQuantity> itemQuantities);
    float getShippingFee();
    float getOrderTotal();
    Invoice getInvoice();
}
