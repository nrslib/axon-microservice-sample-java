package com.example.order.service.http.controller.order;

import com.example.order.service.app.adaptor.aggregates.order.OrderAggregateProtocol;
import com.example.order.service.http.models.order.post.OrderPostRequest;
import com.example.order.service.http.models.order.post.OrderPostResponse;
import com.example.order.shared.application.domain.models.order.OrderId;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Tag(description = "Order API", name = "Order API")
@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final CommandGateway commandGateway;

    public OrderController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @Operation(summary = "Creating a new order.")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderPostResponse post(@RequestBody OrderPostRequest request) {
        var id = new OrderId();
        var command = new OrderAggregateProtocol.IssueOrder(id, request.getAccountId());

        var result = commandGateway.<OrderId>sendAndWait(command);

        return new OrderPostResponse(result.asString());
    }
}
