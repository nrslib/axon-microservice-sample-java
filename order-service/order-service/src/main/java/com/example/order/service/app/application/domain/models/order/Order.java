package com.example.order.service.app.application.domain.models.order;

import com.example.appkit.application.basic.EventDrivenAggregateRoot;
import com.example.item.shared.domain.models.item.ItemId;
import com.example.order.api.domain.models.order.events.OrderEvent;
import com.example.order.api.domain.models.order.events.OrderFailed;
import com.example.order.api.domain.models.order.events.OrderInvoiceLinked;
import com.example.order.api.domain.models.order.events.OrderIssued;
import com.example.order.shared.application.domain.models.order.OrderId;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.Map;
import java.util.UUID;

@Getter
@EqualsAndHashCode
public class Order implements EventDrivenAggregateRoot<OrderEvent> {
    public static OrderIssued create(OrderId orderId, String accountId, Map<UUID, Integer> itemIdToNr, boolean credit) {
        return new OrderIssued(
                orderId,
                accountId,
                itemIdToNr,
                credit
        );
    }
    public static Order applyEvent(OrderIssued event) {
        return new Order(
                event.getOrderId(),
                event.getAccountId(),
                event.isCredit()
        );
    }

    private final OrderId orderId;
    private final String accountId;
    private final boolean credit;
    private boolean errored;

    public Order(OrderId orderId, String accountId, boolean credit) {
        this.orderId = orderId;
        this.accountId = accountId;
        this.credit = credit;
    }

    public OrderInvoiceLinked linkInvoice(UUID invoiceId) {
        return new OrderInvoiceLinked(invoiceId);
    }

    public OrderFailed fail() {
        return new OrderFailed(this.orderId);
    }

    @Override
    public EventDrivenAggregateRoot<OrderEvent> applyEvent(OrderEvent event) {
        if (event instanceof OrderFailed) {
            this.errored = true;
        }

        return this;
    }
}
