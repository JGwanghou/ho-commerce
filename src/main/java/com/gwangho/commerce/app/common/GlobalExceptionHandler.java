package com.gwangho.commerce.app.common;

import com.gwangho.commerce.app.common.exception.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Slf4j
class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handlePriceMismatchException(PriceMismatchException e) {
        log.error("Price Mismatch: {}", e.getMessage());
        ErrorCode errorCode = e.getErrorCode();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(errorCode.getCode(), errorCode.getMessage()));
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleInvalidChargeAmountException(InvalidChargeAmountException e) {
        log.error("Invalid Amount: {}", e.getMessage());
        ErrorCode errorCode = e.getErrorCode();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(errorCode.getCode(), errorCode.getMessage()));
    }

    @ExceptionHandler
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleProductNotFoundException(ProductNotFoundException e) {
        log.error("Product not found: {}", e.getMessage());
        ErrorCode errorCode = e.getErrorCode();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(errorCode.getCode(), errorCode.getMessage()));
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleUserNotFoundException(UserNotFoundException e) {
        log.error("User Not Found: {}", e.getMessage());
        ErrorCode errorCode = e.getErrorCode();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(errorCode.getCode(), errorCode.getMessage()));
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleInsufficientBalanceExceededException(InsufficientBalanceException e) {
        log.error("Insufficient Balance: {}", e.getMessage());
        ErrorCode errorCode = e.getErrorCode();

        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorResponse(errorCode.getCode(), errorCode.getMessage()));
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleOutOfStockException(OutOfStockException e) {
        log.error("Out Of Stock: {}", e.getMessage());
        ErrorCode errorCode = e.getErrorCode();

        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorResponse(errorCode.getCode(), errorCode.getMessage()));
    }
}
