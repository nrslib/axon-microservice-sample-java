package com.example.order.service.app.adaptor.aggregates.order.commands.issue;

import com.example.item.shared.domain.models.item.ItemId;
import com.example.order.service.app.adaptor.aggregates.order.commands.OrderCommand;
import com.example.order.shared.application.domain.models.order.OrderId;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.Map;
import java.util.UUID;

@Getter
@EqualsAndHashCode
public class OrderIssueOrder implements OrderCommand {
    private final OrderId orderId;
    private final String accountId;
    private final Map<UUID, Integer> items;
    private final OrderPaymentInformation paymentInformation;

    public OrderIssueOrder(OrderId orderId, String accountId, Map<UUID, Integer> items, OrderPaymentInformation paymentInformation) {
        this.orderId = orderId;
        this.accountId = accountId;
        this.items = items;
        this.paymentInformation = paymentInformation;
    }
}
