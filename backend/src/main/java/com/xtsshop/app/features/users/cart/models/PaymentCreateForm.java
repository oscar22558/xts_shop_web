package com.xtsshop.app.features.users.cart.models;

import com.xtsshop.app.features.orders.models.PaymentCreateRequest;
import com.xtsshop.app.db.entities.payment.PaymentType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class PaymentCreateForm {
    @NotNull
    @Min(0)
    private Float paidTotal;

    @NotNull
    private PaymentType paymentType;

    public PaymentCreateRequest toRequest(){
        PaymentCreateRequest request = new PaymentCreateRequest();
        request.setPaymentType(paymentType);
        request.setPaidTotal(paidTotal);
        return request;
    }
}
