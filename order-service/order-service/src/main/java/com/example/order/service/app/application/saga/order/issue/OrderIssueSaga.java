package com.example.order.service.app.application.saga.order.issue;

import com.example.item.shared.domain.models.securestock.SecureStockId;
import com.example.order.api.domain.models.order.OrderEvents;
import org.apache.kafka.common.protocol.types.Field;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.SagaLifecycle;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.stream.Collectors;

@Saga
public class OrderIssueSaga {
    @Autowired
    private transient CommandGateway commandGateway;

    @Autowired
    @Value("localhost:8280")
    private transient String paymentHost;

    @Autowired
    @Value("localhost:8180")
    private transient String warehouseHost;

    private OrderIssueSagaState sagaState;

    @StartSaga
    @SagaEventHandler(associationProperty = "orderId")
    public void on(OrderEvents.OrderIssued event) {
        sagaState = new OrderIssueSagaState(event.getOrderId());

        var endpoint = "http://" +warehouseHost +  "/api/secure-stocks";
        var rest = new RestTemplate();
        var items = event.getItemIdToNr().entrySet()
                .stream()
                .map(it -> new ItemAndNr(it.getKey().asString(), it.getValue()));

        var request = new SecureStockPostRequest(
                sagaState.getOrderId().asString(),
                items.collect(Collectors.toList())
        );
        var responseEntity = rest.postForEntity(endpoint, request, SecureStockPostResponse.class);

        if (responseEntity.getStatusCode() == HttpStatus.CREATED) {
            SecureStockId.parseFromString(responseEntity.getBody().getSecureStockId())
                    .either(it -> { throw new RuntimeException(); }, secureStockId -> {
                SagaLifecycle.associateWith("secureStockId", secureStockId.asString());
                return null;
            });
        }
    }
}
