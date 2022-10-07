package com.example.item.service.http.models.item.post;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ItemPostRequest {
    @Schema()
    private String name;
    private int nr;
}
