package com.xtsshop.app.features.users.payment.invoice.models;

import lombok.Getter;

@Getter
public class Invoice {
    private float subItemTotal;
    private float shippingFee;
    private float total;

    public Invoice(float subItemTotal, float shippingFee, float total) {
        this.subItemTotal = subItemTotal;
        this.shippingFee = shippingFee;
        this.total = total;
    }
}
