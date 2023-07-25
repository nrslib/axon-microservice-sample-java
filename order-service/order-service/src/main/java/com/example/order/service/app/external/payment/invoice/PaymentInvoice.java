package com.example.order.service.app.external.payment.invoice;

import com.example.order.service.app.external.payment.invoice.commands.create.PaymentInvoiceCreate;
import com.example.order.service.app.external.payment.invoice.commands.makepayment.PaymentInvoiceMakePayment;
import com.example.order.service.app.external.payment.invoice.events.create.PaymentInvoiceCreateDataCriticalErrorOccurred;
import com.example.order.service.app.external.payment.invoice.events.create.PaymentInvoiceDataCreated;
import com.example.order.service.app.external.services.payment.post.PaymentInvoicePostRequest;
import com.example.order.service.app.external.services.payment.PaymentService;
import com.sun.xml.bind.v2.TODO;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventhandling.gateway.EventGateway;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class PaymentInvoice {
    private final EventGateway eventGateway;
    private final PaymentService paymentService;

    public PaymentInvoice(EventGateway eventGateway, PaymentService paymentService) {
        this.eventGateway = eventGateway;
        this.paymentService = paymentService;
    }

    @CommandHandler
    public void handle(PaymentInvoiceCreate command) {
        var request = new PaymentInvoicePostRequest(command.amount());
        var responseForEntity = paymentService.invoicePost(request);
        if (responseForEntity.getStatusCode() == HttpStatus.CREATED) {
            var event = new PaymentInvoiceDataCreated(responseForEntity.getBody().uuid());
            eventGateway.publish(event);
        } else {
            var event = new PaymentInvoiceCreateDataCriticalErrorOccurred(command.orderId());
            eventGateway.publish(event);
        }
    }

    @CommandHandler
    public void hanle(PaymentInvoiceMakePayment command) {
        // TODO
    }
}
