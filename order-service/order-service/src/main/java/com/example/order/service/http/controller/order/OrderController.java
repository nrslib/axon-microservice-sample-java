package com.example.order.service.http.controller.order;

import com.example.item.shared.domain.models.item.ItemId;
import com.example.order.service.app.application.interactors.order.issue.OrderIssuerInteractor;
import com.example.order.service.http.models.order.post.ItemAndNr;
import com.example.order.service.http.models.order.post.OrderPostRequest;
import com.example.order.service.http.models.order.post.OrderPostResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@Tag(description = "Order API", name = "Order API")
@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final CommandGateway commandGateway;
    private final OrderIssuerInteractor issuerInteractor;

    public OrderController(CommandGateway commandGateway, OrderIssuerInteractor issuerInteractor) {
        this.commandGateway = commandGateway;
        this.issuerInteractor = issuerInteractor;
    }

    @Operation(summary = "Creating a new order.")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderPostResponse post(@Validated @RequestBody OrderPostRequest request) {
        var items = request.getItems().stream()
                .collect(
                        Collectors.toMap(
                                itemAndNr -> ItemId.parseFromString(
                                        itemAndNr.getItemId()).either(error -> {
                                    throw error;
                                }, it -> it), ItemAndNr::getNr
                        )
                );

        var orderId = issuerInteractor.handle(request.getAccountId(), items);

        return new OrderPostResponse(orderId.asString());
    }
}
