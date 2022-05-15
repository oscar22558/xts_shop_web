package com.xtsshop.app.form.orders;

import com.xtsshop.app.db.entities.payment.PaymentType;
import com.xtsshop.app.request.orders.PaymentCreateRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
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
    @NotBlank
    private String cardNum;
    @NotBlank
    private String holderName;
    @NotBlank
    private String bankCode;

    public PaymentCreateRequest toRequest(float itemPriceTotal){
        PaymentCreateRequest request = new PaymentCreateRequest();
        request.setPaymentType(paymentType);
        request.setCardNum(cardNum);
        request.setHolderName(holderName);
        request.setBankCode(bankCode);
        request.setPaidTotal(paidTotal);
        request.setOrderItemPriceTotal(itemPriceTotal);
        return request;
    }

}
