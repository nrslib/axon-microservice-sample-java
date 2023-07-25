package com.example.order.service.app.external.payment.invoice.events.create;

import com.example.order.shared.application.domain.models.order.OrderId;

public record PaymentInvoiceCreateDataCriticalErrorOccurred(OrderId orderId) {
}
