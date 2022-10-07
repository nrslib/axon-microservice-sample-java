package com.example.item.service.http.models.item.stock;

import lombok.Data;

import javax.validation.constraints.Min;

@Data
public class ItemStockRequest {
    @Min(1)
    private int nr;
}
