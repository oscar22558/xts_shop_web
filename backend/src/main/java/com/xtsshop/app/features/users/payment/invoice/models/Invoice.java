package com.xtsshop.app.features.users.payment.invoice.models;

import lombok.Getter;
import lombok.Setter;

@Getter
public class Invoice {
    private float subItemTotal;
    private float shippingFree;
    private float total;

    public Invoice(float subItemTotal, float shippingFree, float total) {
        this.subItemTotal = subItemTotal;
        this.shippingFree = shippingFree;
        this.total = total;
    }
}
