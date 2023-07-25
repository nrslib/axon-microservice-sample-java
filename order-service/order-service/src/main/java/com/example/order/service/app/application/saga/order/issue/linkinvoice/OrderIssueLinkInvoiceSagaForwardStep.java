package com.example.order.service.app.application.saga.order.issue.linkinvoice;

import com.example.order.service.app.adaptor.aggregates.order.commands.linkinvoice.OrderLinkInvoice;
import com.example.order.service.app.external.payment.invoice.events.create.PaymentInvoiceDataCreated;
import com.example.order.shared.application.domain.models.order.OrderId;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.messaging.annotation.MetaDataValue;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class OrderIssueLinkInvoiceSagaForwardStep {
    private final CommandGateway commandGateway;

    public OrderIssueLinkInvoiceSagaForwardStep(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @EventHandler
    public void on(PaymentInvoiceDataCreated event, @MetaDataValue("processId")String processId) {
        var orderId = OrderId.parseFromString(processId).right().value();
        var command = new OrderLinkInvoice(orderId, event.invoiceId());
        commandGateway.sendAndWait(command);
    }
}
