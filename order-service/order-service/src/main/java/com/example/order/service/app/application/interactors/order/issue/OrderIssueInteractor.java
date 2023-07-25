package com.example.order.service.app.application.interactors.order.issue;

import com.example.order.service.app.adaptor.aggregates.order.commands.issue.OrderIssueOrder;
import com.example.order.service.app.adaptor.aggregates.order.commands.issue.OrderPaymentInformation;
import com.example.order.shared.application.domain.models.order.OrderId;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;

@Service
public class OrderIssueInteractor {
    private CommandGateway commandGateway;

    public OrderIssueInteractor(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    public OrderId handle(String accountId, Map<UUID, Integer> items, OrderPaymentInformation paymentInformation) {
        var orderId = new OrderId();

        var command = new OrderIssueOrder(orderId, accountId, items, paymentInformation);
        var resultOrderId = commandGateway.<OrderId>sendAndWait(command);

        return resultOrderId;
    }
}
