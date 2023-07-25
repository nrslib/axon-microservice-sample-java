package com.example.order.service.app.external.services.payment;

import com.example.order.service.app.external.services.payment.makepayment.PaymentInvoiceMakePaymentRequest;
import com.example.order.service.app.external.services.payment.makepayment.PaymentInvoiceMakePaymentResponse;
import com.example.order.service.app.external.services.payment.post.PaymentInvoicePostRequest;
import com.example.order.service.app.external.services.payment.post.PaymentInvoicePostResponse;
import org.springframework.http.ResponseEntity;

public interface PaymentService {
    ResponseEntity<PaymentInvoicePostResponse> invoicePost(PaymentInvoicePostRequest request);
    ResponseEntity<PaymentInvoiceMakePaymentResponse> invoiceMakePayment(PaymentInvoiceMakePaymentRequest request);
}
