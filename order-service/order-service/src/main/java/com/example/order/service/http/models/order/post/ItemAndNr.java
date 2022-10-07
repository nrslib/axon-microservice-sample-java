package com.example.order.service.http.models.order.post;

import com.example.order.service.http.valication.constraints.UUID;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ItemAndNr {
    @Schema(example = "92049b1b-d777-4e66-a4a2-3a6d3716d346")
    @UUID
    private String itemId;
    private int nr;
}
