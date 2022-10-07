package com.example.item.service.http.models.securestock.post;

import lombok.Data;

@Data
public class SecureStockPostResponse {
    private String secureStockId;

    public SecureStockPostResponse(String secureStockId) {
        this.secureStockId = secureStockId;
    }
}
