package com.example.order.service.http.controller.order;

import com.example.order.service.http.models.order.post.OrderPostRequest;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice(basePackageClasses = {OrderController.class})
public class OrderControllerValidationAdvice extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        if (ex.getTarget() instanceof OrderPostRequest) {
            return handlePostValidationError(ex, headers, status, request);
        }

        return super.handleMethodArgumentNotValid(ex, headers, status, request);
    }

    private ResponseEntity<Object> handlePostValidationError(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return super.handleMethodArgumentNotValid(ex, headers, HttpStatus.BAD_REQUEST, request);
    }
}
