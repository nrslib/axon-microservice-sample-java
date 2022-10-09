package com.example.order.service.app.application.saga.order.issue;

import com.example.order.shared.application.domain.models.order.OrderId;
import lombok.Getter;

@Getter
public class OrderIssueSagaState {
    private final OrderId orderId;

    public OrderIssueSagaState(OrderId orderId) {
        this.orderId = orderId;
    }
}
