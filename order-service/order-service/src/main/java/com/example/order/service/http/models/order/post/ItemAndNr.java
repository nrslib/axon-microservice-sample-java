package com.example.order.service.http.models.order.post;

import lombok.Data;

@Data
public class ItemAndNr {
    private String itemId;
    private int nr;
}
