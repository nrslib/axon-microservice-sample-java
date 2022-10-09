package com.example.order.service.app.application.domain.models.order;

import com.example.appkit.application.basic.EventDrivenAggregateRoot;
import com.example.item.shared.domain.models.item.ItemId;
import com.example.order.api.domain.models.order.OrderEvents;
import com.example.order.shared.application.domain.models.order.OrderId;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.Map;

@Getter
@EqualsAndHashCode
public class Order implements EventDrivenAggregateRoot<OrderEvents.Event> {
    private final OrderId orderId;
    private final String accountId;

    public Order(OrderId orderId, String accountId) {
        this.orderId = orderId;
        this.accountId = accountId;
    }
    public static OrderEvents.OrderIssued create(OrderId orderId, String accountId, Map<ItemId, Integer> itemIdToNr) {
        return new OrderEvents.OrderIssued(
                orderId,
                accountId,
                itemIdToNr
        );
    }
    public static Order applyEvent(OrderEvents.OrderIssued event) {
        return new Order(
                event.getOrderId(),
                event.getAccountId()
        );
    }
    @Override
    public EventDrivenAggregateRoot<OrderEvents.Event> applyEvent(OrderEvents.Event event) {
        return this;
    }
}
