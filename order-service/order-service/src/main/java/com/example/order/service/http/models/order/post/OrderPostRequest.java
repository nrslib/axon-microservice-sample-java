package com.example.order.service.http.models.order.post;

import lombok.Data;

import java.util.List;

@Data
public class OrderPostRequest {
    private String accountId;
    private List<ItemAndNr> items;
}
