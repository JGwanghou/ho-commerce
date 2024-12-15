package com.gwangho.commerce.app.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
@Slf4j
class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleProductNotFoundException(ProductNotFoundException e) {
        log.error("Product not found: {}", e.getMessage());
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

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handlePriceMismatchException(PriceMismatchException e) {
        log.error("Price Mismatch: {}", e.getMessage());
        ErrorCode errorCode = e.getErrorCode();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(errorCode.getCode(), errorCode.getMessage()));
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleUserNotFoundException(UserNotFoundException e) {
        log.error("User Not Found: {}", e.getMessage());
        ErrorCode errorCode = e.getErrorCode();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(errorCode.getCode(), errorCode.getMessage()));
    }
}
