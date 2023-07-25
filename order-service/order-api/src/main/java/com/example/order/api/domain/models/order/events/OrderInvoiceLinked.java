package com.example.order.api.domain.models.order.events;

import java.util.UUID;

public record OrderInvoiceLinked(UUID invoiceId) implements OrderEvent {
}
