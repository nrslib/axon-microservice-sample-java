package com.example.item.service.http.models.securestock.post;

import lombok.Data;

import java.util.List;

@Data
public class SecureStockPostRequest {
    private String orderId;
    private List<ItemAndNr> items;
}
