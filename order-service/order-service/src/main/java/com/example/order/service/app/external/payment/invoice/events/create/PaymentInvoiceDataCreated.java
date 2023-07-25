package com.example.order.service.app.external.payment.invoice.events.create;

import com.example.order.service.app.external.payment.invoice.events.PaymentInvoiceEvent;

import java.util.UUID;

public record PaymentInvoiceDataCreated(UUID invoiceId) implements PaymentInvoiceEvent {
}
