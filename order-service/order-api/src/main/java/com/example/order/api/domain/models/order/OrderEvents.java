package com.example.order.api.domain.models.order;

import com.example.order.shared.application.domain.models.order.OrderId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

public interface OrderEvents {
    interface Event {
        OrderId getOrderId();
    }

    @Getter
    @EqualsAndHashCode
    public class OrderIssued implements Event {
        private OrderId orderId;
        private String accountId;

        public OrderIssued(OrderId orderId, String accountId) {
            this.orderId = orderId;
            this.accountId = accountId;
        }

        public OrderId getOrderId() {
            return orderId;
        }
    }
}
