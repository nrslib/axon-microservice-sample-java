package com.example.order.service.app.adaptor.aggregates.order.commands.fail;

import com.example.order.service.app.adaptor.aggregates.order.commands.OrderCommand;
import com.example.order.shared.application.domain.models.order.OrderId;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class OrderFailOrder implements OrderCommand {
    private final OrderId orderId;

    public OrderFailOrder(OrderId orderId) {
        this.orderId = orderId;
    }
}
