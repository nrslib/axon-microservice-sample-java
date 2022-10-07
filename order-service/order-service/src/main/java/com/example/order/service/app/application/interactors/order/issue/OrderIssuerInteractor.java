package com.example.order.service.app.application.interactors.order.issue;

import com.example.item.shared.domain.models.item.ItemId;
import com.example.order.service.app.adaptor.aggregates.order.OrderAggregateProtocol;
import com.example.order.shared.application.domain.models.order.OrderId;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class OrderIssuerInteractor {
    private CommandGateway commandGateway;

    public OrderIssuerInteractor(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    public OrderId handle(String accountId, Map<ItemId, Integer> items) {
        var orderId = new OrderId();

        var command = new OrderAggregateProtocol.IssueOrder(orderId, accountId, items);
        var resultOrderId = commandGateway.<OrderId>sendAndWait(command);

        return resultOrderId;
    }
}
