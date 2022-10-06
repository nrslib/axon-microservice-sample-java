package com.example.order.service.http.models.order.post;

import lombok.Getter;

@Getter
public class OrderPostResponse {
    private String orderId;

    public OrderPostResponse(String orderId) {
        this.orderId = orderId;
    }
}
