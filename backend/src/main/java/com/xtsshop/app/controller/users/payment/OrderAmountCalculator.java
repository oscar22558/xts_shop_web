package com.xtsshop.app.controller.users.payment;

import com.xtsshop.app.controller.users.payment.models.ItemQuantity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderAmountCalculator {

    private UserPaymentRepo userPaymentRepo;

    public OrderAmountCalculator(UserPaymentRepo userPaymentRepo) {
        this.userPaymentRepo = userPaymentRepo;
    }

    public Long calculate(List<ItemQuantity> itemQuantities){
        List<Float> subTotalOfItems = itemQuantities
                .stream()
                .map(itemQuantity->{
                    long itemId = itemQuantity.getItemId();
                    int orderedQuantity = itemQuantity.getQuantity();
                    float currentPrice = userPaymentRepo
                            .getCurrentItemPriceByItemId(itemId);
                    return currentPrice * orderedQuantity;
                })
                .collect(Collectors.toList());
        float total = subTotalOfItems.stream().reduce(0f, Float::sum) * 100;
        return (long)Math.round(total);
    }
}
