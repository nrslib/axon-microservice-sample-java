package com.example.order.service.http.controller.order;

import com.example.order.service.app.application.interactors.order.issue.OrderIssueInteractor;
import com.example.order.service.http.models.order.post.ItemAndNr;
import com.example.order.service.http.models.order.post.OrderPostRequest;
import com.example.order.service.http.models.order.post.OrderPostResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.stream.Collectors;

@Tag(description = "Order API", name = "Order API")
@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final CommandGateway commandGateway;
    private final OrderIssueInteractor issueInteractor;

    public OrderController(CommandGateway commandGateway, OrderIssueInteractor issueInteractor) {
        this.commandGateway = commandGateway;
        this.issueInteractor = issueInteractor;
    }

    @Operation(summary = "Creating a new order.")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderPostResponse post(@Validated @RequestBody OrderPostRequest request) {
        var items = request.getItems().stream()
                .collect(
                        Collectors.toMap(
                                itemAndNr -> UUID.fromString(itemAndNr.getItemId()), ItemAndNr::getNr
                        ));

        var orderId = issueInteractor.handle(request.getAccountId(), items, request.getPaymentInformation());

        return new OrderPostResponse(orderId.asString());
    }
}