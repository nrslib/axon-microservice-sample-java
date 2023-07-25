package com.example.order.service.app.adaptor.aggregates.order.commands;

import com.example.order.shared.application.domain.models.order.OrderId;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

public interface OrderCommand {
    @TargetAggregateIdentifier
    OrderId getOrderId();
}
