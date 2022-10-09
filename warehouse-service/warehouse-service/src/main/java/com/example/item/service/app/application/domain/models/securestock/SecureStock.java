package com.example.item.service.app.application.domain.models.securestock;

import com.example.appkit.application.basic.EventDrivenAggregateRoot;
import com.example.item.shared.domain.models.securestock.SecureStockId;
import com.example.warehouse.api.domain.models.securestock.SecureStockEvents;
import lombok.Getter;

@Getter
public class SecureStock implements EventDrivenAggregateRoot<SecureStockEvents.Event> {
    public static SecureStockEvents.SecureStockIssued create(SecureStockId secureStockId) {
        return new SecureStockEvents.SecureStockIssued(secureStockId);
    }

    public static SecureStock applyEvent(SecureStockEvents.SecureStockIssued event) {
        return new SecureStock(event.getSecureStockId());
    }

    private final SecureStockId secureStockId;

    public SecureStock(SecureStockId secureStockId) {
        this.secureStockId = secureStockId;
    }

    @Override
    public EventDrivenAggregateRoot<SecureStockEvents.Event> applyEvent(SecureStockEvents.Event event) {
        return this;
    }
}
