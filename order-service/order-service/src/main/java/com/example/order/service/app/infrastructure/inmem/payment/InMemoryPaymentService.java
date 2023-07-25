package com.example.order.service.app.infrastructure.inmem.payment;

import com.example.order.service.app.external.services.payment.makepayment.PaymentInvoiceMakePaymentRequest;
import com.example.order.service.app.external.services.payment.makepayment.PaymentInvoiceMakePaymentResponse;
import com.example.order.service.app.external.services.payment.post.PaymentInvoicePostRequest;
import com.example.order.service.app.external.services.payment.post.PaymentInvoicePostResponse;
import com.example.order.service.app.external.services.payment.PaymentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.util.UUID;

public class InMemoryPaymentService implements PaymentService {
    private final String paymentHost;

    public InMemoryPaymentService(@Value("localhost:8280") String paymentHost) {
        this.paymentHost = paymentHost;
    }

    @Override
    public ResponseEntity<PaymentInvoicePostResponse> invoicePost(PaymentInvoicePostRequest request) {
//        return ResponseEntity.notFound()
//                .build();

        return ResponseEntity.created(URI.create(paymentHost + "/api/invoice"))
                .body(new PaymentInvoicePostResponse(UUID.randomUUID()));
    }

    @Override
    public ResponseEntity<PaymentInvoiceMakePaymentResponse> invoiceMakePayment(PaymentInvoiceMakePaymentRequest request) {
        return ResponseEntity.ok(new PaymentInvoiceMakePaymentResponse(request.paymentId()));
    }
}
