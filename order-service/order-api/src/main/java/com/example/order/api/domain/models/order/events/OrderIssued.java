package com.example.order.api.domain.models.order.events;

import com.example.order.shared.application.domain.models.order.OrderId;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;

import java.net.CacheRequest;
import java.util.Map;
import java.util.UUID;

@Data
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS)
public class OrderIssued implements OrderEvent {
    private final OrderId orderId;
    private final String accountId;
    private final Map<UUID, Integer> itemIdToNr;
    private final boolean credit;

    public OrderIssued() {
        this.orderId = new OrderId();
        this.accountId = "";
        this.itemIdToNr = null;
        this.credit = false;
    }

    public OrderIssued(OrderId orderId, String accountId, Map<UUID, Integer> itemIdToNr, boolean credit) {
        this.orderId = orderId;
        this.accountId = accountId;
        this.itemIdToNr = itemIdToNr;
        this.credit = credit;
    }
}
