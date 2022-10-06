package com.example.order.service.app.adaptor.aggregates.order;

import com.example.axon.application.adaptor.AbstractAggregate;
import com.example.order.api.domain.models.order.OrderEvents;
import com.example.order.service.app.application.domain.models.order.Order;
import com.example.order.shared.application.domain.models.order.OrderId;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
public class OrderAggregate extends AbstractAggregate<Order, OrderId, OrderEvents.Event> {
    @Override
    protected OrderId getAggregateRootId(Order aggregate) {
        if (aggregate != null) {
            return aggregate.getOrderId();
        } else {
            return null;
        }
    }

    @Override
    protected Order newAggregateRootByEvent(OrderEvents.Event event) {
        if (event instanceof OrderEvents.OrderIssued) {
            return Order.applyEvent((OrderEvents.OrderIssued) event);
        }

        throw new IllegalArgumentException();
    }

    @Override
    protected boolean isConstructEvent(OrderEvents.Event event) {
        return event instanceof OrderEvents.OrderIssued;
    }

    @CommandHandler
    public OrderAggregate(OrderAggregateProtocol.IssueOrder command) {
        var event = Order.create(command.getOrderId(), command.getAccountId());
        apply(event);
    }

//    @EventSourcingHandler
//    public void handle(OrderEvents.OrderIssued issued) {
//
//    }
}
