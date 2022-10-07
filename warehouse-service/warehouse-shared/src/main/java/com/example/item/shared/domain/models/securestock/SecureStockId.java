package com.example.item.shared.domain.models.securestock;

import com.example.appkit.application.basic.IdObject;
import fj.data.Either;

import java.util.UUID;

public class SecureStockId implements IdObject {
    public static Either<RuntimeException, SecureStockId> parseFromString(String rawId) {
        try {
            return Either.right(
                    new SecureStockId(UUID.fromString(rawId))
            );
        } catch (NumberFormatException ex) {
            return Either.left(ex);
        }
    }

    private final UUID value;

    public SecureStockId(UUID value) {
        this.value = value;
    }

    public SecureStockId() {
        this(UUID.randomUUID());
    }

    @Override
    public String asString() {
        return value.toString();
    }
}
