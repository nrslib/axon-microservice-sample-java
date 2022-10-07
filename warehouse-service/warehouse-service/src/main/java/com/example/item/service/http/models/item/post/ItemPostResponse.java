package com.example.item.service.http.models.item.post;

import lombok.Data;

import java.util.UUID;

@Data
public class ItemPostResponse {
    private final UUID itemId;
    private final String name;
    private final int nr;

    public ItemPostResponse(UUID itemId, String name, int nr) {
        this.itemId = itemId;
        this.name = name;
        this.nr = nr;
    }
}
