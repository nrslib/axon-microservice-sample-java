package com.example.order.shared.application.domain.models.order;

import com.example.appkit.application.basic.IdObject;
import fj.data.Either;
import lombok.Data;

import java.util.UUID;

@Data
public class OrderId implements IdObject {
    private final UUID value;
    public static Either<Exception, OrderId> parseFromString(String rawId) {
        try {
            return Either.right(
                    new OrderId(UUID.fromString(rawId))
            );
        } catch (NumberFormatException ex) {
            return Either.left(ex);
        } catch (Exception ex) {
            throw ex;
        }

    }

    public OrderId(UUID value) {
        this.value = value;
    }

    public OrderId() {
        this(UUID.randomUUID());
    }

    @Override
    public String asString() {
        return value.toString();
    }
}
