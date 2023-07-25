package com.example.order.api.domain.models.order.events;

import com.example.order.shared.application.domain.models.order.OrderId;

public record OrderFailed(OrderId orderId) implements OrderEvent {
}
