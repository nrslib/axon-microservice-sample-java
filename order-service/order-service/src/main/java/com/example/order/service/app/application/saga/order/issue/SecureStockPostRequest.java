package com.example.order.service.app.application.saga.order.issue;

import lombok.Data;

import java.util.List;

@Data
public class SecureStockPostRequest {
    private final String orderId;
    private final List<ItemAndNr> items;

    public SecureStockPostRequest(String orderId, List<ItemAndNr> items) {
        this.orderId = orderId;
        this.items = items;
    }
}
