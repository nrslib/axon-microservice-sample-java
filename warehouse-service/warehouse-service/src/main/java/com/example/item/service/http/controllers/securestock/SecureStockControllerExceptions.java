package com.example.item.service.http.controllers.securestock;

import lombok.Getter;

public interface SecureStockControllerExceptions {
    @Getter
    class PostException extends RuntimeException {
        public PostException(Throwable cause) {
            super(cause);
        }
    }
}
