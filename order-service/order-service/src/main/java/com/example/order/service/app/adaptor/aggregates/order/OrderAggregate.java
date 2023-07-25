package com.example.order.service.app.adaptor.aggregates.order;

import com.example.axon.application.adaptor.AbstractAggregate;
import com.example.order.api.domain.models.order.events.OrderEvent;
import com.example.order.api.domain.models.order.events.OrderIssued;
import com.example.order.service.app.adaptor.aggregates.order.commands.fail.OrderFailOrder;
import com.example.order.service.app.adaptor.aggregates.order.commands.issue.OrderIssueOrder;
import com.example.order.service.app.adaptor.aggregates.order.commands.linkinvoice.OrderLinkInvoice;
import com.example.order.service.app.application.domain.models.order.Order;
import com.example.order.shared.application.domain.models.order.OrderId;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.messaging.MetaData;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.HashMap;

@Aggregate
public class OrderAggregate extends AbstractAggregate<Order, OrderId, OrderEvent> {
    @Override
    protected OrderId getAggregateRootId(Order aggregate) {
        if (aggregate != null) {
            return aggregate.getOrderId();
        } else {
            return null;
        }
    }
    @Override
    protected Order newAggregateRootByEvent(OrderEvent event) {
        if (event instanceof OrderIssued) {
            return Order.applyEvent((OrderIssued) event);
        }

        throw new IllegalArgumentException();
    }
    @Override
    protected boolean isConstructEvent(OrderEvent event) {
        return event instanceof OrderIssued;
    }

    public OrderAggregate() {

    }

    @CommandHandler
    public OrderAggregate(OrderIssueOrder command) {
        var event = Order.create(command.getOrderId(), command.getAccountId(), command.getItems(), command.getPaymentInformation().credit());

        var meta = new MetaData(new HashMap<String, String>(){{
            put("processId", event.getOrderId().asString());
        }});
        apply(event, meta);
    }

    @CommandHandler
    public void handle(OrderLinkInvoice command) {
        var event = getAggregate().linkInvoice(command.invoiceId());
        apply(event);
    }

    @CommandHandler
    public void handle(OrderFailOrder command) {
        var event = getAggregate().fail();
        apply(event);
    }
}
