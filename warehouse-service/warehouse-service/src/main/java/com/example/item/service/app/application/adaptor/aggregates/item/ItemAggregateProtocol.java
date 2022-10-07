package com.example.item.service.app.application.adaptor.aggregates.item;

import com.example.item.shared.domain.models.item.ItemId;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

public interface ItemAggregateProtocol {
    interface Command {
        @TargetAggregateIdentifier
        ItemId getItemId();
    }

    /* Create */
    @Data
    class CreateItem implements Command {
        private final ItemId itemId;
        private final String name;
        private final int nr;

        public CreateItem(ItemId itemId, String name, int nr) {
            this.itemId = itemId;
            this.name = name;
            this.nr = nr;
        }
    }

    @Data
    class CreateItemReply {
        private final CreateItemSummary summary;

        public CreateItemReply(CreateItemSummary summary) {
            this.summary = summary;
        }
    }

    @Data
    class CreateItemSummary {
        private final ItemId itemId;
        private final String name;
        private final int nr;

        public CreateItemSummary(ItemId itemId, String name, int nr) {
            this.itemId = itemId;
            this.name = name;
            this.nr = nr;
        }
    }

    /* Stock */
    @Data
    class StockItem implements Command {
        private final ItemId itemId;
        private final int nr;

        public StockItem(ItemId itemId, int nr) {
            this.itemId = itemId;
            this.nr = nr;
        }
    }
}
