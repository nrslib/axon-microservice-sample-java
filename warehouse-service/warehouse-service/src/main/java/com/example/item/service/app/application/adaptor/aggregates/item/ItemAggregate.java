package com.example.item.service.app.application.adaptor.aggregates.item;

import com.example.axon.application.adaptor.AbstractAggregate;
import com.example.item.service.app.application.domain.models.item.Item;
import com.example.item.shared.domain.models.item.ItemId;
import com.example.warehouse.api.domain.models.item.ItemEvents;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.modelling.command.AggregateCreationPolicy;
import org.axonframework.modelling.command.CreationPolicy;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
public class ItemAggregate extends AbstractAggregate<Item, ItemId, ItemEvents.Event> {
    @Override
    protected ItemId getAggregateRootId(Item aggregate) {
        if (aggregate != null) {
            return aggregate.getItemId();
        } else {
            return null;
        }
    }
    @Override
    protected Item newAggregateRootByEvent(ItemEvents.Event event) {
        if (event instanceof ItemEvents.ItemCreated) {
            return Item.applyEvent((ItemEvents.ItemCreated) event);
        }

        throw new IllegalStateException();
    }
    @Override
    protected boolean isConstructEvent(ItemEvents.Event event) {
        return event instanceof ItemEvents.ItemCreated;
    }

    @CommandHandler
    @CreationPolicy(AggregateCreationPolicy.ALWAYS)
    public ItemAggregateProtocol.CreateItemReply handle(ItemAggregateProtocol.CreateItem command) {
        var createEvent = Item.create(command.getItemId(), command.getName(), command.getNr());
        apply(createEvent);

        return new ItemAggregateProtocol.CreateItemReply(
                new ItemAggregateProtocol.CreateItemSummary(
                        createEvent.getItemId(),
                        createEvent.getName(),
                        createEvent.getNr()
                )
        );
    }

    @CommandHandler
    public void handle(ItemAggregateProtocol.StockItem command) {
        apply(it -> it.stock(command.getNr()));
    }
}
