package com.example.deleveryManagement.handler;

import com.example.deleveryManagement.dto.ErrorResponseDto;
import com.example.deleveryManagement.exception.DeliveryNotFoundException;
import com.example.deleveryManagement.exception.DeliveryStateException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DeliveryNotFoundException.class)
    public Mono<ErrorResponseDto> handleDeliveryNotFound(DeliveryNotFoundException ex, ServerWebExchange exchange) {
        return Mono.just(new ErrorResponseDto(
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                ex.getMessage(),
                exchange.getRequest().getPath().value()
        ));
    }

    @ExceptionHandler(DeliveryStateException.class)
    public Mono<ErrorResponseDto> handleDeliveryStateException(DeliveryStateException ex, ServerWebExchange exchange) {
        return Mono.just(new ErrorResponseDto(
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                ex.getMessage(),
                exchange.getRequest().getPath().value()
        ));
    }

    @ExceptionHandler(Exception.class)
    public Mono<ErrorResponseDto> handleGenericException(Exception ex, ServerWebExchange exchange) {
        return Mono.just(new ErrorResponseDto(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                "Unexpected error occurred",
                exchange.getRequest().getPath().value()
        ));
    }
}
