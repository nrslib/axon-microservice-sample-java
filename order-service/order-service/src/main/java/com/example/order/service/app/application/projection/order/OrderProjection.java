package com.example.order.service.app.application.projection.order;

import com.example.order.api.domain.models.order.OrderEvents;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.ResetHandler;
import org.springframework.stereotype.Service;

@Service
public class OrderProjection {
    private OrderRepository orderRepository;

    public OrderProjection(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @ResetHandler
    public void reset() {
        orderRepository.deleteAll();
    }

    @EventHandler
    public void on(OrderEvents.OrderIssued event) {
        var data = new OrderDataModel();
        data.setItemId(event.getOrderId().asString());
        data.setName("test-name");
        data.setNr(1);

        orderRepository.save(data);
    }
}
