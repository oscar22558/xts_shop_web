package com.xtsshop.app.features.users.payment;

import com.xtsshop.app.db.entities.Invoice;
import com.xtsshop.app.features.users.payment.models.ItemQuantity;
import com.xtsshop.app.helpers.DateTimeHelper;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderTotalCalculatorImp implements OrderTotalCalculator {

    private UserPaymentRepo userPaymentRepo;

    private List<ItemQuantity> itemQuantities;

    public OrderTotalCalculatorImp(UserPaymentRepo userPaymentRepo) {
        this.userPaymentRepo = userPaymentRepo;
    }

    public void setItemQuantities(List<ItemQuantity> itemQuantities) {
        this.itemQuantities = itemQuantities;
    }

    public float getItemsTotal(List<ItemQuantity> itemQuantities){
        List<BigDecimal> subTotalOfItems = itemQuantities
                .stream()
                .map(itemQuantity->{
                    long itemId = itemQuantity.getItemId();
                    int orderedQuantity = itemQuantity.getQuantity();
                    float currentPrice = userPaymentRepo
                            .getCurrentItemPriceByItemId(itemId);
                    return new BigDecimal(currentPrice).multiply(new BigDecimal(orderedQuantity));
                })
                .collect(Collectors.toList());

        BigDecimal total = subTotalOfItems.stream().reduce(new BigDecimal(0), BigDecimal::add);
        return total.floatValue();
    }

    public float getShippingFee(){
        return 20;
    }

    public float getOrderTotal(){
        float itemsTotal = getItemsTotal(itemQuantities);
        float shippingFee = getShippingFee();
        return itemsTotal + shippingFee;
    }

    public Invoice getInvoice(){
        Date now = new DateTimeHelper().now();
        float itemsTotal = getItemsTotal(itemQuantities);
        float shippingFee = getShippingFee();
        float total = getOrderTotal();
        Invoice invoice = new Invoice();
        invoice.setCreatedAt(now);
        invoice.setUpdatedAt(now);
        invoice.setItemsTotal(itemsTotal);
        invoice.setShippingFee(shippingFee);
        invoice.setTotal(total);
        return invoice;
    }
}
