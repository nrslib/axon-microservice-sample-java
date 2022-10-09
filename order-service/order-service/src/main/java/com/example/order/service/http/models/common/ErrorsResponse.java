package com.example.order.service.http.models.common;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ErrorsResponse {
    public static ErrorsResponse apply(Object code, String message) {
        var errors = new ArrayList<ErrorModel>();
        errors.add(new ErrorModel(code, message));

        return new ErrorsResponse(
                errors
        );
    }

    private final List<ErrorModel> errors;

    public ErrorsResponse(List<ErrorModel> errors) {
        this.errors = errors;
    }
}
