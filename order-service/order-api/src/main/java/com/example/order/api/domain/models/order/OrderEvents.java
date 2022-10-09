package com.example.order.api.domain.models.order;

import com.example.item.shared.domain.models.item.ItemId;
import com.example.order.shared.application.domain.models.order.OrderId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.Map;

public interface OrderEvents {
    interface Event {
        OrderId getOrderId();
    }

    @Data
    class OrderIssued implements Event {
        private final OrderId orderId;
        private final String accountId;
        private final Map<ItemId, Integer> itemIdToNr;

        public OrderIssued(OrderId orderId, String accountId, Map<ItemId, Integer> itemIdToNr) {
            this.orderId = orderId;
            this.accountId = accountId;
            this.itemIdToNr = itemIdToNr;
        }
    }
}
