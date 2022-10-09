package com.example.order.service.http.models.common;

import lombok.Data;

@Data
public class ErrorModel {
    private final Object code;
    private final String message;

    public ErrorModel(Object code, String message) {
        this.code = code;
        this.message = message;
    }
}
