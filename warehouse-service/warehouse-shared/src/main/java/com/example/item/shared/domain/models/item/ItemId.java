package com.example.item.shared.domain.models.item;

import com.example.appkit.application.basic.IdObject;
import fj.data.Either;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.UUID;

@Data
public class ItemId implements IdObject {
    public static Either<RuntimeException, ItemId> parseFromString(String rawId) {
        try {
            return Either.right(
                    new ItemId(UUID.fromString(rawId))
            );
        } catch (NumberFormatException ex) {
            return Either.left(ex);
        }
    }

    private final UUID value;

    public ItemId(UUID value) {
        this.value = value;
    }

    public ItemId() {
        this(UUID.randomUUID());
    }

    @Override
    public String asString() {
        return value.toString();
    }
}
