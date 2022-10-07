package com.example.item.service.app.application.adaptor.aggregates.securestock;

import com.example.item.shared.domain.models.item.ItemId;
import com.example.item.shared.domain.models.securestock.SecureStockId;
import com.example.order.shared.application.domain.models.order.OrderId;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.Map;

public interface SecureStockAggregateProtocol {
    interface Command {
        @TargetAggregateIdentifier
        SecureStockId getSecureStockId();
    }

    @Data
    class IssueSecureStock implements Command {
        private final SecureStockId secureStockId;
        private final OrderId orderId;
        private final Map<ItemId, Integer> itemIdToNr;
    }
}
