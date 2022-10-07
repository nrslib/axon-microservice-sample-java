package com.example.item.service.app.application.interactors.item.create;

import com.example.item.service.app.application.adaptor.aggregates.item.ItemAggregateProtocol;
import com.example.item.shared.domain.models.item.ItemId;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;

@Service
public class ItemCreateInteractor {
    private final CommandGateway commandGateway;

    public ItemCreateInteractor(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    public ItemAggregateProtocol.CreateItemReply handle(String name, int nr) {
        var itemId = new ItemId();
        var command = new ItemAggregateProtocol.CreateItem(itemId, name, nr);

        return commandGateway.sendAndWait(command);
    }
}
