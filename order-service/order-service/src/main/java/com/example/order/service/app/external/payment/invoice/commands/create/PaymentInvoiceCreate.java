package com.example.order.service.app.external.payment.invoice.commands.create;

import com.example.order.shared.application.domain.models.order.OrderId;

public record PaymentInvoiceCreate(OrderId orderId, int amount) {
}
