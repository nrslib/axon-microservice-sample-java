package com.example.warehouse.api.domain.models.securestock;

import com.example.item.shared.domain.models.securestock.SecureStockId;
import lombok.Data;

public interface SecureStockEvents {
    interface Event {
        SecureStockId getSecureStockId();
    }

    @Data
    class SecureStockIssued implements Event {
        private final SecureStockId secureStockId;

        public SecureStockIssued(SecureStockId secureStockId) {
            this.secureStockId = secureStockId;
        }
    }
}
