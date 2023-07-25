package com.example.order.service.app.application.saga.order.issue.createinvoice;

import com.example.order.api.domain.models.order.events.OrderIssued;
import com.example.order.service.app.external.payment.invoice.commands.create.PaymentInvoiceCreate;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class OrderIssueCreateInvoiceSagaForwardStep {
    private final CommandGateway commandGateway;

    public OrderIssueCreateInvoiceSagaForwardStep(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @EventHandler
    public void on(OrderIssued event) {
        var testValue = 100 + new Random().nextInt(10);
        var command = new PaymentInvoiceCreate(event.getOrderId(), testValue);
        commandGateway.sendAndWait(command);
    }
}
