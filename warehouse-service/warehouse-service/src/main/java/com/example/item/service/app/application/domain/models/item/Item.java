package com.example.item.service.app.application.domain.models.item;

import com.example.appkit.application.basic.EventDrivenAggregateRoot;
import com.example.item.shared.domain.models.item.ItemId;
import com.example.warehouse.api.domain.models.item.ItemEvents;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.apache.commons.lang3.NotImplementedException;

@Getter
@EqualsAndHashCode
public class Item implements EventDrivenAggregateRoot<ItemEvents.Event> {
    private final ItemId itemId;

    public Item(ItemId itemId) {
        this.itemId = itemId;
    }
    public static ItemEvents.ItemCreated create(ItemId itemId, String name, int nr) {
        return new ItemEvents.ItemCreated(
                itemId,
                name,
                nr
        );
    }
    public static Item applyEvent(ItemEvents.ItemCreated event) {
        return new Item(event.getItemId());
    }
    public ItemEvents.ItemStocked stock(int nr) {
        return new ItemEvents.ItemStocked(
                itemId,
                nr
        );
    }

    @Override
    public EventDrivenAggregateRoot<ItemEvents.Event> applyEvent(ItemEvents.Event event) {
        if (event instanceof ItemEvents.ItemCreated) {
            return this;
        } else if (event instanceof ItemEvents.ItemStocked) {
            return this;
        } else {
            throw new NotImplementedException();
        }
    }
}
