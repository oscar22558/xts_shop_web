package com.xtsshop.app.features.users.payment.invoice;

import com.xtsshop.app.features.users.payment.OrderTotalCalculator;
import com.xtsshop.app.features.users.payment.invoice.models.Invoice;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class InvoiceController {
    private OrderTotalCalculator orderAmountCalculator;

    public InvoiceController(OrderTotalCalculator orderAmountCalculator) {
        this.orderAmountCalculator = orderAmountCalculator;
    }

    @PostMapping("api/users/invoice")
    public ResponseEntity<?> getInvoice(
        @RequestBody @Valid GetInvoiceRequest getInvoiceRequest
    ){
        float subItemTotal = orderAmountCalculator.getItemsTotal(getInvoiceRequest.getItemQuantities());
        float shippingFree = 20f;
        float total = subItemTotal + shippingFree;
        Invoice invoice = new Invoice(subItemTotal, shippingFree, total);
        return ResponseEntity.ok(invoice);
    }
}
