package com.example.order.service.app.application.saga.order.issue.makepayment;

import com.example.order.api.domain.models.order.events.OrderInvoiceLinked;
import com.example.order.service.app.adaptor.aggregates.order.OrderAggregate;
import com.example.order.service.app.external.payment.invoice.commands.makepayment.PaymentInvoiceMakePayment;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.messaging.annotation.MetaDataValue;
import org.springframework.stereotype.Component;

@Component
public class OrderIssueMakePaymentSagaForwardStep {
    private final CommandGateway commandGateway;

    public OrderIssueMakePaymentSagaForwardStep(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @EventHandler
    public void on(OrderInvoiceLinked event, @MetaDataValue("processId") String processId, EventSourcingRepository<OrderAggregate> eventSourcingRepository) {
        eventSourcingRepository.load(processId)
                .execute(aggregate -> {
                    if (aggregate.getAggregate().isCredit()) {
                        commandGateway.send(new PaymentInvoiceMakePayment(event.invoiceId()));
                    }
                });
    }
}
