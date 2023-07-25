package com.example.order.service.app.application.saga.order.issue.createinvoice;

import com.example.order.service.app.adaptor.aggregates.order.commands.fail.OrderFailOrder;
import com.example.order.service.app.external.payment.invoice.events.create.PaymentInvoiceCreateDataCriticalErrorOccurred;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
public class OrderIssueCreateInvoiceSagaBackwardStep {
    private final CommandGateway commandGateway;

    public OrderIssueCreateInvoiceSagaBackwardStep(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @EventHandler
    public void on(PaymentInvoiceCreateDataCriticalErrorOccurred event) {
        commandGateway.send(new OrderFailOrder(event.orderId()));
    }
}
