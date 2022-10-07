package com.example.order.service.app.adaptor.aggregates.order;

import com.example.item.shared.domain.models.item.ItemId;
import com.example.order.shared.application.domain.models.order.OrderId;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.Map;

public interface OrderAggregateProtocol {
    interface Command {
        @TargetAggregateIdentifier
        public OrderId getOrderId();
    }

    @Getter
    @EqualsAndHashCode
    class IssueOrder implements Command {
        private final OrderId orderId;
        private final String accountId;
        private final Map<ItemId, Integer> items;

        public IssueOrder(OrderId orderId, String accountId, Map<ItemId, Integer> items) {
            this.orderId = orderId;
            this.accountId = accountId;
            this.items = items;
        }
    }
}
