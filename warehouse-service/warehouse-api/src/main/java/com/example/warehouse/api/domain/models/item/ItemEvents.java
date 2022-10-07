package com.example.warehouse.api.domain.models.item;

import com.example.item.shared.domain.models.item.ItemId;
import lombok.Data;

public interface ItemEvents {
    interface Event {
        ItemId getItemId();
    }

    @Data
    class ItemCreated implements Event {
        private final ItemId itemId;
        private final String name;
        private final int nr;

        public ItemCreated(ItemId itemId, String name, int nr) {
            this.itemId = itemId;
            this.name = name;
            this.nr = nr;
        }
    }

    @Data
    class ItemStocked implements Event {
        private final ItemId itemId;
        private final int nr;

        public ItemStocked(ItemId itemId, int nr) {
            this.itemId = itemId;
            this.nr = nr;
        }
    }
}
