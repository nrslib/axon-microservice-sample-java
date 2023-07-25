package com.example.order.service.config.infrastructure;

import com.example.order.service.app.external.services.payment.PaymentService;
import com.example.order.service.app.infrastructure.sdk.payment.PaymentServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile({"prod", "axon", "kafka-single", "kafka-single-sasl"})
public class InfrastructureConfiguration {
    @Bean
    public PaymentService paymentService(@Value("localhost:8280") String paymentHost) {
        return new PaymentServiceImpl(paymentHost);
    }
}
