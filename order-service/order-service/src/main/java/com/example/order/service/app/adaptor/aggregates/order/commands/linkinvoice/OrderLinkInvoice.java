package com.example.order.service.app.adaptor.aggregates.order.commands.linkinvoice;

import com.example.order.service.app.adaptor.aggregates.order.commands.OrderCommand;
import com.example.order.shared.application.domain.models.order.OrderId;

import java.util.UUID;

public record OrderLinkInvoice(OrderId orderId, UUID invoiceId) implements OrderCommand {
    @Override
    public OrderId getOrderId() {
        return orderId;
    }
}