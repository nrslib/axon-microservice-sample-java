package com.example.order.api.domain.models.order;

import com.example.order.shared.application.domain.models.order.OrderId;
import lombok.EqualsAndHashCode;
import lombok.Getter;

public interface OrderEvents {
    interface Event {
        OrderId getOrderId();
    }

    @Getter
    @EqualsAndHashCode
    class OrderIssued implements Event {
        private final OrderId orderId;
        private final String accountId;

        public OrderIssued(OrderId orderId, String accountId) {
            this.orderId = orderId;
            this.accountId = accountId;
        }
    }
}
