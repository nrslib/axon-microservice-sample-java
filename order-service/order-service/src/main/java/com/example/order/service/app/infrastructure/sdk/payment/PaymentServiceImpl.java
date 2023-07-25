package com.example.order.service.app.infrastructure.sdk.payment;

import com.example.order.service.app.external.services.payment.makepayment.PaymentInvoiceMakePaymentRequest;
import com.example.order.service.app.external.services.payment.makepayment.PaymentInvoiceMakePaymentResponse;
import com.example.order.service.app.external.services.payment.post.PaymentInvoicePostRequest;
import com.example.order.service.app.external.services.payment.post.PaymentInvoicePostResponse;
import com.example.order.service.app.external.services.payment.PaymentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class PaymentServiceImpl implements PaymentService {
    private final String paymentHost;

    public PaymentServiceImpl(@Value("localhost:8280") String paymentHost) {
        this.paymentHost = paymentHost;
    }

    @Override
    public ResponseEntity<PaymentInvoicePostResponse> invoicePost(PaymentInvoicePostRequest request) {
        var endpoint = "http://" + paymentHost + "/api/invoice";
        var rest = new RestTemplate();

        return rest.postForEntity(endpoint, request, PaymentInvoicePostResponse.class);
    }

    @Override
    public ResponseEntity<PaymentInvoiceMakePaymentResponse> invoiceMakePayment(PaymentInvoiceMakePaymentRequest request) {
        var endpoint = "http://" + paymentHost + "/api/invoice/make-payment";
        var rest = new RestTemplate();

        return rest.postForEntity(endpoint, request, PaymentInvoiceMakePaymentResponse.class);
    }
}
