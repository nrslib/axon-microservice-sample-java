package com.example.order.service.http.models.order.post;

import com.example.order.service.app.adaptor.aggregates.order.commands.issue.OrderPaymentInformation;
import com.example.order.service.http.valication.constraints.UUID;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.Valid;
import java.util.List;

@Data
public class OrderPostRequest {
    @Schema(example = "92049b1b-d777-4e66-a4a2-3a6d3716d346")
    @UUID
    private String accountId;

    @Valid
    private List<ItemAndNr> items;

    private OrderPaymentInformation paymentInformation;
}
