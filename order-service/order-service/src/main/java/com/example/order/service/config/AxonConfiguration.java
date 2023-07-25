package com.example.order.service.config;

import com.example.order.service.app.adaptor.aggregates.order.OrderAggregate;
import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.messaging.correlation.CorrelationDataProvider;
import org.axonframework.messaging.correlation.MessageOriginProvider;
import org.axonframework.messaging.correlation.MultiCorrelationDataProvider;
import org.axonframework.messaging.correlation.SimpleCorrelationDataProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class AxonConfiguration {    @Bean
    public CorrelationDataProvider correlationDataProvider() {
        return new MultiCorrelationDataProvider<CommandMessage<?>>(
                List.of(
                        new SimpleCorrelationDataProvider("processId"),
                        new MessageOriginProvider()
                )
        );
    }

    @Bean
    public EventSourcingRepository<OrderAggregate> orderAggregateEventSourcingRepository(EventStore eventStore) {
        return EventSourcingRepository.builder(OrderAggregate.class).eventStore(eventStore).build();
    }
}
