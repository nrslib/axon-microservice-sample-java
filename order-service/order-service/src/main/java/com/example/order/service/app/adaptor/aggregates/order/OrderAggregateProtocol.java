package com.example.order.service.app.adaptor.aggregates.order;

import com.example.order.shared.application.domain.models.order.OrderId;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

public interface OrderAggregateProtocol {
    public interface Command {
        @TargetAggregateIdentifier
        public OrderId getOrderId();
    }

    @Getter
    @EqualsAndHashCode
    public class IssueOrder implements Command {
        private OrderId orderId;
        private String accountId;

        public IssueOrder(OrderId orderId, String accountId) {
            this.orderId = orderId;
            this.accountId = accountId;
        }
    }
}
