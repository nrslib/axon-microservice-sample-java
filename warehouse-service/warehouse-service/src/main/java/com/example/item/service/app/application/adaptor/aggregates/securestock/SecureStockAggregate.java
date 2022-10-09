package com.example.item.service.app.application.adaptor.aggregates.securestock;

import com.example.axon.application.adaptor.AbstractAggregate;
import com.example.item.service.app.application.domain.models.securestock.SecureStock;
import com.example.item.shared.domain.models.securestock.SecureStockId;
import com.example.warehouse.api.domain.models.securestock.SecureStockEvents;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
public class SecureStockAggregate extends AbstractAggregate<SecureStock, SecureStockId, SecureStockEvents.Event> {
    @Override
    protected SecureStockId getAggregateRootId(SecureStock aggregate) {
        if (aggregate == null) {
            return null;
        }
        return aggregate.getSecureStockId();
    }
    @Override
    protected SecureStock newAggregateRootByEvent(SecureStockEvents.Event event) {
        if (event instanceof SecureStockEvents.SecureStockIssued) {
            return SecureStock.applyEvent((SecureStockEvents.SecureStockIssued)event);
        }

        throw new IllegalStateException();
    }
    @Override
    protected boolean isConstructEvent(SecureStockEvents.Event event) {
        return event instanceof SecureStockEvents.SecureStockIssued;
    }

    @CommandHandler
    public SecureStockAggregate(SecureStockAggregateProtocol.IssueSecureStock command) {
        var event = SecureStock.create(command.getSecureStockId());
        apply(event);
    }
}
