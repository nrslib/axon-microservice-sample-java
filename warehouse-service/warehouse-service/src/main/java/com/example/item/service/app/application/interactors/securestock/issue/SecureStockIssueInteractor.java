package com.example.item.service.app.application.interactors.securestock.issue;

import com.example.item.service.app.application.adaptor.aggregates.securestock.SecureStockAggregateProtocol;
import com.example.item.service.app.application.domain.models.securestock.SecureStock;
import com.example.item.shared.domain.models.item.ItemId;
import com.example.item.shared.domain.models.securestock.SecureStockId;
import com.example.order.shared.application.domain.models.order.OrderId;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class SecureStockIssueInteractor {
    private final CommandGateway commandGateway;

    public SecureStockIssueInteractor(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    public SecureStockId handle(OrderId orderId, Map<ItemId, Integer> itemIdToNr) {
        var secureStockId = new SecureStockId();
        var command = new SecureStockAggregateProtocol.IssueSecureStock(secureStockId, orderId, itemIdToNr);

        return commandGateway.sendAndWait(command);
    }
}
