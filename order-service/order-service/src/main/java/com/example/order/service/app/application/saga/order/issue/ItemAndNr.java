package com.example.order.service.app.application.saga.order.issue;

import lombok.Data;

@Data
public class ItemAndNr {
    private final String itemId;
    private final int nr;

    public ItemAndNr(String itemId, int nr) {
        this.itemId = itemId;
        this.nr = nr;
    }
}
