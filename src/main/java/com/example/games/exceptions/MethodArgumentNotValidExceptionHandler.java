package com.example.games.exceptions;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.http.HttpStatus;
import org.springframework.web.context.request.WebRequest;

import java.time.Instant;
import java.util.stream.Collectors;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class MethodArgumentNotValidExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handeValidationErrors(MethodArgumentNotValidException ex, WebRequest request) {
        return ErrorResponse.builder()
                .type(ErrorType.BAD_REQUEST)
                .message(ex.getMessage())
                .errors(ex.getBindingResult()
                        .getFieldErrors()
                        .stream()
                        .map((f) -> f.getDefaultMessage())
                        .collect(Collectors.toList()))
                .timeStamp(Instant.now().toEpochMilli())
                .build();
    }
}